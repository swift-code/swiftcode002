package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.EnumValue;

import javax.persistence.*;

/**
 * Created by lubuntu on 8/20/16.
 */

@Entity
public class ConnectionRequest extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne
    public User sender;

    @ManyToOne
    public User receiver;

    public Status status;

    public enum Status
    {
        @EnumValue(value = "WAITING")
        WAITING,
        @EnumValue(value = "ACCEPTED")
        ACCEPTED
    }

}
