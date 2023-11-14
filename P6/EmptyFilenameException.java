package P6;

import java.io.IOException;

public class EmptyFilenameException extends IOException{
	public EmptyFilenameException(){
		super();
	}
	
	public EmptyFilenameException(String message){
		super(message);
	}

}
