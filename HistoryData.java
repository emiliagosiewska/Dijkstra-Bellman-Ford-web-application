package lab5Algorithms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lab5Algorithms.model.Algorithm;

/**
 * An entity that contains the columns as id, name of the chosen algorithm,
 * given source node and obtained result
 *
 * @author Emilia Gosiewska version 5.0
 */
@Entity
@Table(name = "History_Data")
public class HistoryData implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "choosen_algorithm", nullable = false)
    private String algorithm;

    @Column(name = "chosen_node", nullable = false)
    private int sourceNode;

    @Column(name = "distance", nullable = false)
    private String distanceValue;

    @OneToOne(optional = false)
    @JoinColumn(name = "data_id", referencedColumnName = "id")
    private Data data;

    /**
     * an empty contructor
     */
    public HistoryData() {

    }

    /**
     * a method that sets the elements
     *
     * @param id default id
     * @param algorithm the name of the chosen algorithm
     * @param sourceNode given source node
     * @param distanceValue distance value calculated from the adjList, given
     * source node and chosen algorithm
     * @param data an object
     */
    public void HistoryData(Integer id, String algorithm, int sourceNode, String distanceValue, Data data) {
        this.id = id;
        this.algorithm = algorithm;
        this.sourceNode = sourceNode;
        this.distanceValue = distanceValue;
        this.data = data;
    }

    /**
     * getter of id
     *
     * @return return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * getter of algorithm
     *
     * @return return algorithm
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * getter of source node
     *
     * @return return source node
     */
    public int getSourceNode() {
        return sourceNode;
    }

   /**
    * setter of id
    * @param id sets the id
    */
    public void setId(Integer id) {
        this.id = id;
    }
/**
 * setter of algorithm
 * @param algorithm  sets the algorithm
 */
    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
/**
 * setter of source node
 * @param sourceNode sets the source node 
 */
    public void setSourceNode(int sourceNode) {
        this.sourceNode = sourceNode;
    }
/**
 * getter of the data
 * @return returns the data
 */
    public Data getData() {
        return data;
    }
/**
 * setter of the data
 * @param data sets data
 */
    public void setData(Data data) {
        this.data = data;
    }
/**
 * getter of distance value
 * @return returns distance value
 */
    public String getDistanceValue() {
        return distanceValue;
    }
/**
 * setter of distance value
 * @param distanceValue sets the distance value
 */
    public void setDistanceValue(String distanceValue) {
        this.distanceValue = distanceValue;
    }

    /**
     * override method that performs hash operations
     * @return returns hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.algorithm);
        hash = 29 * hash + this.sourceNode;
        hash = 29 * hash + Objects.hashCode(this.distanceValue);
        hash = 29 * hash + Objects.hashCode(this.data);
        return hash;
    }
/**
 * override method that checks the equality
 * @param obj object of the default class Object
 * @return returns bool
 */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HistoryData other = (HistoryData) obj;
        if (this.sourceNode != other.sourceNode) {
            return false;
        }
        if (!Objects.equals(this.algorithm, other.algorithm)) {
            return false;
        }
        if (!Objects.equals(this.distanceValue, other.distanceValue)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }
/**
 * overide method that returns parameters
 * @return returns parameters
 */
    @Override
    public String toString() {
        return id + ", " + sourceNode + ", " + algorithm + ", " + distanceValue;
    }

}
