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

	//Application menu

	public int showMenu() {
		int option=0;

		System.out.println(
				"\n<<<APPLICATION MENU>>>\n"+
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


	//Game Menu
	public int showGameMenu() {
		int option=0;

		System.out.println(
				"\n<<<GAME MENU>>>\n"+
				"\nSelect an option\n" +
				"(1)Poner tuberia\n" +
				"(2)Simular\n" +
                "(3)Salir \n"
				);

		option= sc.nextInt();
		sc.nextLine();
		System.out.println("");
		return option;
	}



	//executeOpetation of the menu of the application 

	public void executeOperation(int operation) {
		
		switch(operation) {
		case 0:
			System.out.println("Bye");
			break;
		

		//Case 1 = new game 

		case 1:

			// create the game board
			newGame();

			

			//do while to the menu of the game 

			int option = 0;
			do{
				option= showGameMenu();
				executeGameOpetation(option);
				
			}while (option!=3);

			break;
		
		// case 2 = simulate the game	
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

	// create the game board
    public void newGame(){

		String name= "";

        System.out.println("Digite el nombre del jugador");
        name=sc.next();
        sc.nextLine();


		control.createBoard(name);
	}

	public int positionOfThePipe(){

		System.out.println("Inserte el la ubicación de la pipe");
		System.out.println("");
		System.out.println("Número de la fila");
		int row = sc.nextInt();
		sc.nextLine();


		System.out.println("");
		System.out.println("Número de la columna");
		

		int column = sc.nextInt();
		sc.nextLine();

		return column*row;
	
	}


	


	// execute operations of the game

	public void executeGameOpetation(int operation){
		switch(operation){

			case 1:

				System.out.println(control.BoardtoString());

				int position=positionOfThePipe();
				System.out.println("Seleccione el tipo de tuberia que desea cambiar\n(1) HORIZONTAL\n(2) CIRCULAR\n(3) EMPTY\n(4) VERTICAL");
				int typeOfPipe=sc.nextInt();

				control.changePipe(position, typeOfPipe);
				System.out.println(control.BoardtoString());
				
				break;

			case 2: 

				break;

	


			
			default:
				System.out.println("Going back to the menu of the application");
		}

	}





    
}
