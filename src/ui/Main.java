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
				
			}while (option!=0);

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


	// execute operations of the game

	public void executeGameOpetation(int operation){
		switch(operation){

			case 1:

				break;

			case 2: 

				break;

			// case 3 = exit to the game and return to the application menu
			case 3:

				int option = 0;
				do{
					option= showMenu();
					executeOperation(option);
					
				}while (option!=0);

				break;

			
			default:
				System.out.println("invalid option");
		}

	}





    
}
