package com.silicolife.anote2daemon.model.pojo;
// Generated 16/Jan/2015 15:20:27 by Hibernate Tools 4.0.0



/**
 * ProcessEntitiesClassStatsId generated by hbm2java
 */
public class ProcessEntitiesClassStatsId  implements java.io.Serializable {


     private long processesIdprocesses;
     private String class_;
     private long count(classesIdclasses);

    public ProcessEntitiesClassStatsId() {
    }

    public ProcessEntitiesClassStatsId(long processesIdprocesses, String class_, long count(classesIdclasses)) {
       this.processesIdprocesses = processesIdprocesses;
       this.class_ = class_;
       this.count(classesIdclasses) = count(classesIdclasses);
    }
   
    public long getProcessesIdprocesses() {
        return this.processesIdprocesses;
    }
    
    public void setProcessesIdprocesses(long processesIdprocesses) {
        this.processesIdprocesses = processesIdprocesses;
    }
    public String getClass_() {
        return this.class_;
    }
    
    public void setClass_(String class_) {
        this.class_ = class_;
    }
    public long getCount(classesIdclasses)() {
        return this.count(classesIdclasses);
    }
    
    public void setCount(classesIdclasses)(long count(classesIdclasses)) {
        this.count(classesIdclasses) = count(classesIdclasses);
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ProcessEntitiesClassStatsId) ) return false;
		 ProcessEntitiesClassStatsId castOther = ( ProcessEntitiesClassStatsId ) other; 
         
		 return (this.getProcessesIdprocesses()==castOther.getProcessesIdprocesses())
 && ( (this.getClass_()==castOther.getClass_()) || ( this.getClass_()!=null && castOther.getClass_()!=null && this.getClass_().equals(castOther.getClass_()) ) )
 && (this.getCount(classesIdclasses)()==castOther.getCount(classesIdclasses)());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getProcessesIdprocesses();
         result = 37 * result + ( getClass_() == null ? 0 : this.getClass_().hashCode() );
         result = 37 * result + (int) this.getCount(classesIdclasses)();
         return result;
   }   


}


