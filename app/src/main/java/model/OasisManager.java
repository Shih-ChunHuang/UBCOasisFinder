package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import model.exception.OasisException;

/**
 * Created by Shih-Chun on 2017-05-27.
 */

public class OasisManager implements Iterable<Oasis> {

    private Set<Oasis> oasises;
    private static OasisManager instance;
    private Oasis selected;


    /**
     * Constructs oasis manager with empty set of oasis and null as the selected oasis
     */
    private OasisManager() {
        selected = null;
        oasises = new HashSet<Oasis>();

    }

    /**
     * Gets one and only instance of this class
     *
     * @return  instance of class
     */
    public static OasisManager getInstance() {
        if (instance == null) {
            instance = new OasisManager();
        }
        return instance;
    }

    public Oasis getSelected() {
        return selected;
    }

    public void setSelected(Oasis selected) throws OasisException{
        if (oasises.contains(selected)) {
            this.selected = selected;
        }else {
            throw new OasisException("No such oasis in OasisManager");
            

        }
    }

    public void clearSelectedOasis() {
        selected = null;
    }

    public Set<Oasis> getOasises() {
        return oasises;
    }

    /**
     * Get number of oasis managed
     *
     * @return  number of oasis added to manager
     */
    public int getNumOasis(){
        return oasises.size();
    }


    public void clearOasis(){
        oasises.clear();
    }

    @Override
    public Iterator<Oasis> iterator() {
        return oasises.iterator();
    }
}
