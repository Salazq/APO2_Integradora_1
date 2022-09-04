package model;

public class Pipe {

    private int value;
    private String id;
    private Pipe next;
    private Pipe previous;

    public Pipe (int value){
        this.value=value;
        next=null;
    }

    public String toString(){
        
        return ""+ value;
    }

    public Pipe getNext(){
        
        return next;
    }

    public void setNext(Pipe next){
        
        this.next=next;
    }

    public Pipe getPrevious(){
        
        return previous;
    }

    public void setPrevious(Pipe previous){
        
        this.previous=previous;
    }


}
