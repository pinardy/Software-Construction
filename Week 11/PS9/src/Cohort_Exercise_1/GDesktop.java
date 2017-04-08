package Cohort_Exercise_1;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class GDesktop {
	private final static int BOUND = 20;
	private final static int N_CONSUMERS = 4;
	private static final ExecutorService exec = new ScheduledThreadPoolExecutor(10);


	public static void startIndexing (File[] roots) {
		BlockingQueue<File> queue = new LinkedBlockingQueue<File>(BOUND);
		FileFilter filter = new FileFilter() {
			public boolean accept(File file) {return true;}
		};
		
		for (File root : roots) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try{
                        crawl(root, filter, queue);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });

		}
		
		for (int i = 0; i < N_CONSUMERS; i++) {
			(new Indexer(queue)).start();
		}
	}
    private static void crawl(File root, FileFilter filter, BlockingQueue<File> queue) throws InterruptedException {
        File[] entries = root.listFiles(filter);

        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry, filter, queue);
                }
                else {
                    queue.put(entry);
                }
            }
        }
    }
}

class FileCrawler extends Thread {
	private final BlockingQueue<File> fileQueue; 
	private final FileFilter fileFilter; 	
	private final File root;
	
	FileCrawler (BlockingQueue<File> queue, FileFilter filter, File root) {
		this.fileQueue = queue;
		this.fileFilter = filter;
		this.root = root;
	}
	
	public void run() {
		try {
			crawl(root);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	public void crawl(File root) throws InterruptedException {
		File[] entries = root.listFiles(fileFilter);
		
		if (entries != null) {
			for (File entry : entries) {
				if (entry.isDirectory()) {
					crawl(entry);
				}
				else {
					fileQueue.put(entry);	
				}
			}
		}
	}
}

class Indexer extends Thread {
	private final BlockingQueue<File> queue;
	
	public Indexer (BlockingQueue<File> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			while (true) {
				indexFile(queue.take());
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void indexFile(File file) {
		// TODO Auto-generated method stub	
	}
}