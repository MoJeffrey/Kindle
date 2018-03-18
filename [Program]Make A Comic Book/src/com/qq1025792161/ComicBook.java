package com.qq1025792161;

import java.io.File;

import com.qq1025792161.DownloadPitures.DownloadPictures;
import com.qq1025792161.OrganizePicturesAndProfiles.*;
import com.qq1025792161.CarriedOutKindlegen.*;;

public class ComicBook {
	String DownloadURL;
	String FileName;
	String ComicsName;
	String Aut;
	 
	File ComicsFile;
	DownloadPictures DownloadPictures;
	PicturesAndProfiles PicturesAndProfiles;
	
	public ComicBook(String DownloadURL) {
		this.DownloadURL=DownloadURL;
		
		
		this.DownloadPictures=DownloadPictures();
		this.ComicsName=DownloadPictures.GetComicsName();
		this.FileName=DownloadPictures.GetFileName();
		this.Aut=DownloadPictures.GetAut();
		this.ComicsFile=DownloadPictures.GetComicsFile();
		
		this.PicturesAndProfiles=PicturesAndProfiles();
		
		CarriedOutKindlegen();
		
		PicturesAndProfiles.FindMobiFileAndCopy(ComicsFile);
//		PicturesAndProfiles.deletePosition(ComicsFile);
	}
	
	private DownloadPictures DownloadPictures() {
		return new DownloadPictures(DownloadURL);
	}
	
	private PicturesAndProfiles PicturesAndProfiles() {
		return new PicturesAndProfiles(ComicsFile,ComicsName,Aut);
	}
	
	private CarriedOutKindlegen CarriedOutKindlegen() {
		return new CarriedOutKindlegen(PicturesAndProfiles.GetProfilesForOPFFile(),FileName);
	}
}
