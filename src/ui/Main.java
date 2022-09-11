package ui;
import java.util.Random;
import java.util.Scanner;
import java.util.ResourceBundle.Control;

import model.BoardControl;

public class Main{

    private BoardControl control;
	private Scanner sc;
	private String name;
	private long starExecution=0;
	private boolean simulator=false;
	
	public Main(){

        control= new BoardControl();

		control.createScore(100000, "pedrooooooooooooooo");
		control.createScore(102, "juan");
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
				
				if(simulator==true){
					option=3;
				}else{
					option= showGameMenu();
				}
				executeGameOpetation(option);
				
			}while (option!=3);

			break;
		
		// case 2 = simulate the game	
		case 2:

			if (control.getRoot()!=null){
				control.scoreToString(control.createArray());
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

		name="";

        System.out.println("Digite el nombre del jugador");
        name=sc.next();
        sc.nextLine();
		

		control.createBoard(name);
		starExecution = System.nanoTime();
	}



	public int [] positionOfThePipe(){

		
		
		System.out.println("Inserte el la ubicación de la pipe");
		System.out.println("");
		System.out.println("Número de la fila");
		int row = sc.nextInt();
		sc.nextLine();


		System.out.println("");
		System.out.println("Número de la columna");
		

		int column = sc.nextInt();
		sc.nextLine();

		int [] position = {column, row};

		return position;
	}



	// execute operations of the game

	public void executeGameOpetation(int operation){

		control.BoardtoString();
		System.out.println("");
		switch(operation){

			case 1:

				int [] position=positionOfThePipe();
				int typeOfPipe=0;
				

				
				// do while for the type of pipe
				do {

				System.out.println("Seleccione el tipo de tuberia que desea cambiar\n(1) HORIZONTAL\n(2) CIRCULAR\n(3) EMPTY\n(4) VERTICAL");
				typeOfPipe=sc.nextInt();

				if(typeOfPipe==5 || typeOfPipe==6) System.out.println("Error opcion no valida");

				}while(typeOfPipe>4 || typeOfPipe<=0);

				control.selectType(control.searchPipe(position, control.getHead()), typeOfPipe);

				control.BoardtoString();
				
				break;

			case 2: 
				
				simulator =control.preSimulate();
				
				if(simulator==true){
					double endExecution = (System.nanoTime()-starExecution)/ 1e9;
					
					control.createScore(control.calculateScore(endExecution), name);

				}

				break;

			default:
				System.out.println("Going back to the menu of the application");
		}

	}





    
}
