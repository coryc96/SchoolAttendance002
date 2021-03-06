package cc.schoolattendance002;

/**
 * Created by cchambers on 11/27/17.
 */

public class Student {

    int _id;
    String _name;
    String _email;
    int _isPresent;

    public Student(){}
    public Student(int id, String name, String email, int _isPresent){
        this._id = id;
        this._name = name;
        this._email = email;
        this._isPresent = _isPresent;

    }

    public int get_ID(){
        return this._id;
    }
    public void set_ID(int id){
        this._id = id;
    }

    public String get_Name(){
        return this._name;
    }

    public void set_Name(String name){
        this._name = name;
    }

    public String get_Email(){
        return this._email;
    }

    public void set_Email(String email){
        this._email = email;
    }

    public int get_isPresent(){
        return this._isPresent;
    }

    public void set_isPresent(int isPresent){
        this._isPresent = isPresent;
    }
}
