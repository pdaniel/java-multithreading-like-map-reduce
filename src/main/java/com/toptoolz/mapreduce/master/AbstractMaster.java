package com.toptoolz.mapreduce.master;

import com.toptoolz.mapreduce.map.Mapper;
import com.toptoolz.mapreduce.master.exception.MasterException;
import com.toptoolz.mapreduce.reduce.Reducer;
import com.toptoolz.mapreduce.task.Task;
import com.toptoolz.mapreduce.worker.AbstractWorker;
import com.toptoolz.mapreduce.worker.Worker;
import com.toptoolz.mapreduce.worker.WorkerFactory;

import java.util.List;
import java.util.Vector;

/**
 * @author: danielpo
 * Date: 7/10/13
 * Time: 11:23 AM
 * ideea este ca pentru faill over as putea crea ca fiecare worker sa poata juca rol de master la un moment dat si ar putea el
 * sa aloce taskuri catre ceilalri eorkeri. Ar trebui cumva sa am o lista de workeri inregistrati si la un moment dat unul sa fie
 * marcat ca si master
 */
public abstract class AbstractMaster implements Master{

    Vector<AbstractWorker> workers = new Vector<>();
    Task task;
   // String data;
    List input;
    Mapper mapper;
    Reducer reducer;
    int workersNo;

    protected AbstractMaster(List input, Mapper mapper, Reducer reducer) {
        this.input = input;
        this.mapper = mapper;
        this.reducer = reducer;
    }

    protected AbstractMaster(Reducer reducer, Mapper mapper, List input, int workersNo) {
        this.reducer = reducer;
        this.mapper = mapper;
        this.input = input;
        this.workersNo = workersNo;
    }
    /*   protected AbstractMaster(Task task, String data) {
        this.task = task;
        this.data = data;
    }*/

    protected AbstractMaster(Task task, List input) {
        this.task = task;
        this.input = input;
    }

    protected AbstractMaster(final Task task) {
        this.task = task;
    }


    public void registerWorker(AbstractWorker abstractWorker){
        workers.add(abstractWorker);
    }

    public void deleteWorker(AbstractWorker abstractWorker){
        workers.remove(abstractWorker);
    }

    protected void createWorkers(int taskNumOfWorkers){
        int workersSize = workers.size();
        if(workersSize<taskNumOfWorkers){
            int dif = taskNumOfWorkers-workersSize;
            for(int i=0;i<dif;i++){
                registerWorker(WorkerFactory.getWorker(mapper));
            }
        }
    }


    public void forTestCreateWorkers(){
        createWorkers(task.noOfWorkers());
    }

    public Worker forTestAvailableWorker(){
        return getAvailableworker();
    }

    protected Worker getAvailableworker(){
        return getAvailableworker(0);
    }
    protected AbstractWorker getAvailableworker(int idx){
        AbstractWorker worker;
        int workersSize = workers.size();
        if(workersSize>0 && idx < workersSize){
           worker = workers.get(idx);
           if(!worker.isRunning() && !worker.isTaken()){
               worker.setTaken(true);
               return worker;
           }else{
               return getAvailableworker(idx+1);
           }
        }
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            throw new MasterException(e);
        }
        return getAvailableworker(0);
    }

    public Vector<AbstractWorker> getWorkers() {
        return workers;
    }
}
