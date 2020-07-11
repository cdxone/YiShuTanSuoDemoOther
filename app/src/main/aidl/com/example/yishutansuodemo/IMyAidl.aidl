package com.example.yishutansuodemo;
import com.example.yishutansuodemo.bean.Person;

interface IMyAidl {
    void addPerson(in Person person);
    List<Person> getPersonList();
}
