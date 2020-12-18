package med.bouguern.head.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import med.bouguern.head.model.FileModel;

@Transactional
public interface FileRepository extends JpaRepository<FileModel, Long>{
	

}
