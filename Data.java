/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5Algorithms.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * An entity that contains columns as: size of the graph, first and second
 * vertex and itd weight
 *
 * @author Emilia Gosiewska version 5.0
 */
@Entity
@Table(name = "Given_Data")
public class Data implements Serializable {

//    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "sizeOfGraph", nullable = false)
    private int size;
    @Column(name = "x_vertex", nullable = false)
    private int x;
    @Column(name = "y_vertex", nullable = false)
    private int y;
    @Column(name = "weight", nullable = false)
    private int weight;

    /**
     * an empty contructor
     */
    public Data() {

    }

    /**
     * a method that sets the elements
     *
     * @param id default id
     * @param x the vakue of the first vertex
     * @param y the value of the second vertex
     * @param weight the weight of the vertices
     * @param size given size of the graph
     */
    public void Data(Integer id, Integer x, Integer y, Integer weight, Integer size) {

        this.id = id;
        this.size = size;
        this.x = x;
        this.y = y;
        this.weight = weight;

    }

    /**
     * getter of id
     *
     * @return returns id
     */

    public Integer getId() {
        return id;
    }

    /**
     * getter of size
     *
     * @return returns size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * getter of x
     *
     * @return returns x
     */
    public Integer getX() {
        return x;
    }

    /**
     * getter of y
     *
     * @return returns y
     */
    public Integer getY() {
        return y;
    }

    /**
     * getter of weight
     *
     * @return returns weight
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * setter of id
     *
     * @param id sets id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * setter of the size
     *
     * @param size sets size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * setter of the x
     *
     * @param x returns x
     */
    public void setX(Integer x) {
        this.x = x;
    }

    /**
     * setter of the y
     *
     * @param y returns y
     */
    public void setY(Integer y) {
        this.y = y;
    }

    /**
     * setter of the weight
     *
     * @param weight returns weight
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * override method that performs hash operations
     *
     * @return returns hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + this.size;
        hash = 97 * hash + this.x;
        hash = 97 * hash + this.y;
        hash = 97 * hash + this.weight;
        return hash;
    }

    /**
     * override method that checks the equality
     *
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
        final Data other = (Data) obj;
        if (this.size != other.size) {
            return false;
        }
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        if (this.weight != other.weight) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * overide method that returns parameters
     *
     * @return returns parameters
     */
    @Override
    public String toString() {
        return id + ", " + size + ", " + x + ", " + y + ", " + weight;
    }

}
