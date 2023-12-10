public class Item {
    private String name;
    private int amount;
    private String desc;

    Item(){
        name = "plant";
        amount = 1;
        desc = "green";
    }

    Item(String name, int amount, String desc){
       this.name = name;
       this.amount = amount;
       this.desc = desc; 
    }

    public int getAmount(){
        return this.amount;
    } 

    public void updateAmount(){
        this.amount++;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString(){
        return this.name + " is " + this.desc + " of amount: " + this.amount;
    }
}
