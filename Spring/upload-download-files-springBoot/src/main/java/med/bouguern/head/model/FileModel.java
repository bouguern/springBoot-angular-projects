package med.bouguern.head.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "file_model")
public class FileModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@JsonView(View.FileInfo.class)
	private Long id;
	
	@Column(name = "name")
	@JsonView(View.FileInfo.class)
	private String name;
	
	@Column(name = "mimeType")
	private String mimeType;
	
	@Lob
	@Column(name = "pic")
	private byte[] pic;
	
	public FileModel(){}
	  
	  public FileModel(String name, String mimeType, byte[] pic){
	    this.name = name;
	    this.mimeType = mimeType;
	    this.pic = pic;
	  }
	  
	  public Long getId(){
	    return this.id;
	  }
	  
	  public void setId(Long id){
	    this.id = id;
	  }
	  
	  public String getName(){
	    return this.name;
	  }
	  
	  public void setName(String name){
	    this.name = name;
	  }
	  
	  public String getMimetype(){
	    return this.mimeType;
	  }
	  
	  public void setMimetype(String mimeType){
	    this.mimeType = mimeType;
	  }
	  
	  public byte[] getPic(){
	    return this.pic;
	  }
	  
	  public void setPic(byte[] pic){
	    this.pic = pic;
	  }

}
