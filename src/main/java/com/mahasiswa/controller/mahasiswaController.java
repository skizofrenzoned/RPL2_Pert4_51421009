package com.mahasiswa.controller;
 
import com.mahasiswa.model.hibernateUtil;
import com.mahasiswa.model.modelMahasiswa;
import java.util.List;
import org.hibernate.*;
import org.hibernate.query.Query;
 
public class mahasiswaController {
    public void addMhs(modelMahasiswa mhs){
        Transaction trx = null;
        
        try(Session session = hibernateUtil.getSessionFactory().openSession()){
            trx = session.beginTransaction();
            session.save(mhs);
            trx.commit();
        }catch (Exception e){
            if (trx != null){
                trx.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public void updateMhs(modelMahasiswa mhs){
        Transaction trx = null;
        
        try (Session session = hibernateUtil.getSessionFactory().openSession()){
            trx = session.beginTransaction();
            session.update(mhs);
            trx.commit();
        }catch (Exception e){
            if (trx != null){
                trx.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public void deleteMhs(int id){
        Transaction trx = null;
        
        try (Session session = hibernateUtil.getSessionFactory().openSession()){
            trx = session.beginTransaction();
            modelMahasiswa mhs = session.get(modelMahasiswa.class, id);
            if(mhs != null){
                session.delete(mhs);
                System.out.println("Berhasil dihapus");
            }
            trx.commit();
        }catch (Exception e){
            if (trx != null){
                trx.rollback();
            }
            e.printStackTrace();
        }
    }
    
    public List<modelMahasiswa> getAllMahasiswa(){
        Transaction trx = null;
        List<modelMahasiswa> listMhs = null;
        
        try (Session session = hibernateUtil.getSessionFactory().openSession()){
            trx = session.beginTransaction();
            Query<modelMahasiswa> query = session.createQuery("from modelMahasiswa", modelMahasiswa.class);
            listMhs = query.list();
            trx.commit();
        }catch (Exception e){
            if (trx != null){
                trx.rollback();
            }
            e.printStackTrace();
        }
        return listMhs;
    }
}
