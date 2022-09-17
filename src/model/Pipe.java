package model;

public class Pipe {

    private PipeType type;
    private int id;
    private Pipe next;
    private Pipe previous;

    public Pipe (int id){
        type=PipeType.EMPTY;
        next=null;
        this.id=id;
    }

    public void printerBoard(){

        switch (type){

            case EMPTY:

                System.out.printf("%1s %1s %1s","[","x","]");
                break;

            case CIRCULAR:

                System.out.printf("%1s %1s %1s","[","o","]");
                break;

            case FINAL:

                System.out.printf("%1s %1s %1s","[","D","]");
                break;
            
            case START:

                System.out.printf("%1s %1s %1s","[","F","]");

                break;
            
            
            case HORIZONTAL:

                System.out.printf("%1s %1s %1s","[","=","]");

                break;

            case VERTICAL:

            System.out.printf("%1s","[| |]");

                break;
        }
    }

    /** 
     * @return Pipe
     */
    public Pipe getNext(){
        
        return next;
    }

    /** 
     * @param next
     */
    public void setNext(Pipe next){
        
        this.next=next;
    }

    /** 
     * @return Pipe
     */
    public Pipe getPrevious(){
        
        return previous;
    }

    /** 
     * @param previous
     */
    public void setPrevious(Pipe previous){
        
        this.previous=previous;
    }
    
    /** 
     * @return int
     */
    public int getId(){
        return id;
    }

    /** 
     * @param type
     */
    public void setType(PipeType type){
        this.type=type;
    }

    /** 
     * @return PipeType
     */
    public PipeType getType(){
        return type;
    }
}
