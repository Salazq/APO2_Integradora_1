package ui;
import java.util.Scanner;
import model.BoardControl;

public class Main{

    private BoardControl control;
	private Scanner sc;
	
	public Main(){

        control= new BoardControl();
		control.createScore(2, "A");
		control.createScore(1, "B");
		control.createScore(4, "C");
		control.createScore(3, "D");
		control.createScore(5, "E");

		sc= new Scanner(System.in);
    }

    public static void main(String[] args) {

		Main m = new Main();
		
		int option = 0;
		do{
			option= m.showMenu();
			m.executeOperation(option);
			
		}while (option!=0);

	}

	public int showMenu() {
		int option=0;

		System.out.println(
				"\nSelect an option\n" +
				"(1)Nueva partida\n" +
				"(2)Ver puntaje\n" +
                "(0)Salir \n"
				);

		option= sc.nextInt();
		sc.nextLine();
		System.out.println("");
		return option;
	}


	public int showGameMenu() {
		int option=0;

		System.out.println(
				"\nSelect an option\n" +
				"(1)Poner tuberia\n" +
				"(2)Simular\n" +
                "(0)Salir \n"
				);

		option= sc.nextInt();
		sc.nextLine();
		System.out.println("");
		return option;
	}

	public void executeOperation(int operation) {
		
		switch(operation) {
		case 0:
			System.out.println("Bye");
			break;

		case 1:

			break;


		case 2:

			if (control.getRoot()!=null){
				System.out.println(control.scoreToString(control.createArray()));
			}
			else{
				System.out.println("No hay scores registrados");
			}
			break;

		default:
			System.out.println("invalid option");
		
		}
	}

    public void newGame(){

		String name= "";

        System.out.println("Digite el nombre del jugador");
        name=sc.next();
        sc.nextLine();


		control.createBoard(name);
	}





    
}
