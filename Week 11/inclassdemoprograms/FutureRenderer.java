//import java.io.*;
//import java.math.BigInteger;
//import java.net.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.Callable;
//import java.util.concurrent.Executor;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.Future;
//import java.util.concurrent.ScheduledThreadPoolExecutor;
//
//import Week10.ImageData;
//import Week10.ImageInfo;
//
//public class FutureRenderer {
//    // creating an executor with scheduled thread pool
//	private final ExecutorService executor = new ScheduledThreadPoolExecutor (100);
//
//	void renderPage (CharSequence source) throws Exception {
//		final List<ImageInfo> imageInfos = scanForImageInfo(source);
//
//		// create task about 'downloading images' of a webpage
//		Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
//			public List<ImageData> call () {
//				List<ImageData> result = new ArrayList<ImageData>();
//				for (ImageInfo imageInfo : imageInfos) {
//					result.add(imageInfo.downloadImage());
//				}
//
//				return result;
//			}
//		};
//		// submitting the 'download' task to executor
//		Future<List<ImageData>> future = executor.submit(task);
//		renderText(source);
//
//		/* future.get() throws InterruptedException.
//		It is therefore important to set the interruption status
//		of the callback by calling it in the try-catch block
//		 */
//
//		try {
//			List<ImageData> imageData = future.get();
//			for (ImageData data: imageData) {
//				renderImage(data);
//			}
//		} catch (InterruptedException e) {
//			Thread.currentThread().interrupt(); //why?
// // because we want to restore the status of the interrupt flag
//			future.cancel(true);
//		}
//	}
//
//	private void renderImage(ImageData data) {
//		// TODO Auto-generated method stub
//
//	}
//
//	private void renderText(CharSequence source) {
//		// TODO Auto-generated method stub
//
//	}
//
//	private List<ImageInfo> scanForImageInfo(CharSequence source) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//}