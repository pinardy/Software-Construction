import java.util.Scanner;

public class PizzaStore {

    public static void main(String[] args) {
        Pizza pizza = null;

        PizzaFactory pizzaFactory = new PizzaFactory();

        Scanner userInput = new Scanner(System.in);
        System.out.print("What type of pizza? (cheese / greek / pepperoni)");

        if (userInput.hasNextLine()){
            String typeOfPizza = userInput.nextLine();
            pizzaFactory.makePizza(typeOfPizza);

            if(typeOfPizza != null){
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
                System.out.println("Pizza prepared!");
            } else {
                System.out.print("Please enter (cheese / greek / pepperoni) next time");
            }
        }
    }

}

class PizzaFactory {
	public Pizza makePizza(String newPizzaType){
		Pizza newPizza = null;

		if (newPizzaType.equals("cheese")){
			return new CheesePizza();
		}

		else if (newPizzaType.equals("greek")){
			return new GreekPizza();
		}

        else if (newPizzaType.equals("pepperoni")){
			return new GreekPizza();
		}
		else return null;
	}
}

class Pizza {

	public void prepare() {
        System.out.println("Preparing pizza...");
    }

	public void box() {
        System.out.println("Boxing pizza...");
    }

	public void cut() {
        System.out.println("Cutting pizza...");
    }

	public void bake() {
        System.out.println("Baking pizza...");
    }
}

class CheesePizza extends Pizza {}
class GreekPizza extends Pizza {}
class PepperoniPizza extends Pizza {}

