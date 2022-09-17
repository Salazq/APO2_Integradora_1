package ui;
import java.util.Scanner;


import model.BoardControl;

public class Main{

    private BoardControl control;
	private Scanner sc;
	private String name;
	private long starExecution=0;
	private boolean simulator=false;
	
	public Main(){

        control= new BoardControl();
		sc= new Scanner(System.in);
    }
    
	/** 
	 * @param args
	 */
	public static void main(String[] args) {

		Main m = new Main();
		
		int option = 0;
		do{
			option= m.showMenu();
			m.executeOperation(option);
			
		}while (option!=0);
	}

	/** 
	 * @return int
	 */
	//Application menu

	public int showMenu() {
		int option=0;

		System.out.println(
				"\n<<<MENU>>>\n"+
				"\nSelecciona una opcion\n" +
				"(1)Nueva partida\n" +
				"(2)Ver puntaje\n" +
                "(0)Salir \n"
				);

		option= sc.nextInt();
		sc.nextLine();
		System.out.println("");
		return option;
	}


	
	/** Game Menu
	 * @return int
	 */
	public int showGameMenu() {
		int option=0;

		System.out.println(
				"\n\n<<<MENU DEL JUEGO>>>\n"+
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

	/** executeOpetation of the menu of the application 
	 * @param operation
	 */
	public void executeOperation(int operation) {
		
		switch(operation) {
		case 0:
			System.out.println("Adios");
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
					simulator=false;
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
				System.out.println("No hay puntuaciones registradas");
			}
			break;

		default:
			System.out.println("Opcion invalida");
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
		control.BoardtoString();
	}

	/** 
	 * @return int[]
	 */
	public int [] positionOfThePipe(){

		System.out.println("Inserte el la ubicación de la pipe");
		System.out.println("");
		System.out.println("Número de la fila entre (0 y 7)");
		int row = sc.nextInt()+1;
		sc.nextLine();

		System.out.println("");
		System.out.println("Número de la columna entre (0 y 7)");
		
		int column = sc.nextInt()+1;
		sc.nextLine();

		if(row<1){
			row=1;
		}
		if(row>8){
			row=8;
		}
		if(column<1){
			column=1;
		}
		if(column>8){
			column=8;
		}
		
		
		int [] position = {column, row};

		return position;
	}

	/** 
	 * @param operation
	 */
	// execute operations of the game

	public void executeGameOpetation(int operation){

		System.out.println("");
		switch(operation){

			case 1:

				int [] position=positionOfThePipe();
				int typeOfPipe=0;
				
				// do while for the type of pipe
				do {

					System.out.println("Seleccione el tipo de tuberia que desea cambiar\n(1) = \n(2) o\n(3) x\n(4) ||");
					typeOfPipe=sc.nextInt();

					if(typeOfPipe>4 || typeOfPipe<1) System.out.println("Opcion no valida");

				}while(typeOfPipe>4 || typeOfPipe<1);

				control.selectType(control.searchPipe(position, control.getHead()), typeOfPipe);

				control.BoardtoString();
				
				break;

			case 2: 
				
				simulator =control.preSimulate();
				
				if(simulator==true){
					double endExecution = (System.nanoTime()-starExecution)/ 1e9;
					
					control.createScore(control.calculateScore(endExecution), name);

					System.out.println("La solución es correcta");
					System.out.println("Tu puntaje fue de: " + String.format("%.2f", control.calculateScore(endExecution)));

					System.out.println("\nVolviendo al menu de la aplicacion...");

				}else{

					System.out.println("La solución no es correcta");
				}
				break;

			default:
				System.out.println("");
		}
	} 
}
