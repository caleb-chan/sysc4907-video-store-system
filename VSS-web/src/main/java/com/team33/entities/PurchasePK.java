package com.team33.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Represents the primary key for purchases
 *
 * @author Samual
 */
@Embeddable
public class PurchasePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "Orders_id")
    private int ordersid;
    @Basic(optional = false)
    @Column(name = "Orders_Account_id")
    private int ordersAccountid;
    @Basic(optional = false)
    @Column(name = "Video_Info_id")
    private int videoInfoid;

    /**
     * Constructs the primary key for purchase
     */
    public PurchasePK() {
    }

    /**
     * Constructs the primary key for purchase
     *
     * @param id
     * @param ordersid
     * @param ordersAccountid
     * @param videoInfoid
     */
    public PurchasePK(int id, int ordersid, int ordersAccountid, int videoInfoid) {
        this.id = id;
        this.ordersid = ordersid;
        this.ordersAccountid = ordersAccountid;
        this.videoInfoid = videoInfoid;
    }

    /**
     * Retrieves the id for the primary key
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the orders id
     *
     * @return
     */
    public int getOrdersid() {
        return ordersid;
    }

    /**
     * Sets orders id
     *
     * @param ordersid
     */
    public void setOrdersid(int ordersid) {
        this.ordersid = ordersid;
    }

    /**
     * Retrieves the orders account id
     *
     * @return
     */
    public int getOrdersAccountid() {
        return ordersAccountid;
    }

    /**
     * Sets the orders account id
     *
     * @param ordersAccountid
     */
    public void setOrdersAccountid(int ordersAccountid) {
        this.ordersAccountid = ordersAccountid;
    }

    /**
     * Retrieves the video info id
     *
     * @return
     */
    public int getVideoInfoid() {
        return videoInfoid;
    }

    /**
     * Sets the video info id
     *
     * @param videoInfoid
     */
    public void setVideoInfoid(int videoInfoid) {
        this.videoInfoid = videoInfoid;
    }

    /**
     * hashes the purchase PK
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) ordersid;
        hash += (int) ordersAccountid;
        hash += (int) videoInfoid;
        return hash;
    }

    /**
     * Returns true if objects are equal
     *
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchasePK)) {
            return false;
        }
        PurchasePK other = (PurchasePK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.ordersid != other.ordersid) {
            return false;
        }
        if (this.ordersAccountid != other.ordersAccountid) {
            return false;
        }
        if (this.videoInfoid != other.videoInfoid) {
            return false;
        }
        return true;
    }

    /**
     * Represents the purchase PK as a string
     *
     * @return
     */
    @Override
    public String toString() {
        return "javaapplication5.PurchasePK[ id=" + id + ", ordersid=" + ordersid + ", ordersAccountid=" + ordersAccountid + ", videoInfoid=" + videoInfoid + " ]";
    }
}
