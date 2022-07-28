package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LensManager implements Iterable<Lens> {
    private List<Lens> lens = new ArrayList<>();

    public void addLenses(Lens len){
        lens.add(len);
    }
    public Lens search(int i){
        return lens.get(i);
    }
    public  int totalLenses(){
        return lens.size();
    }
    public void printAll(){
        for(int i = 0 ; i < lens.size() ; i++){
            System.out.println( i + " is " +
            lens.get(i).toString()
            );
        }
    }

    @Override
    public Iterator<Lens> iterator() {
        return null;
    }


}