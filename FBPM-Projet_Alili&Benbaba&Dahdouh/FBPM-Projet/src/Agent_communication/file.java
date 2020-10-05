package Agent_communication;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 *
 * @author Rym
 */
@SuppressWarnings("serial")
public class file implements Serializable{

private byte[]  f;
private String fileName;
private String description;

public file() {
	super();
	// TODO Auto-generated constructor stub
}

public file(byte[] f, String fileName, String description) {
	super();
	this.f = f;
	this.fileName = fileName;
	this.description = description;
}

public byte[] getF() {
	return f;
}

public void setF(byte[] f) {
	this.f = f;
}

public String getFileName() {
	return fileName;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}


}

