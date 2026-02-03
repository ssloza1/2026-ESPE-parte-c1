package es.upm.grise.profundizacion.file;

import java.util.ArrayList;
import java.util.List;

public class File {

    private FileType type;
    private List<Character> content;
	private FileUtils fileUtils;

	/*
	 * Constructor
	 */
    public File() {
		this.content = new ArrayList<>();
		this.fileUtils = new FileUtils();
    }

	/*
	 * Method to code / test
	 */
    public void addProperty(char[] content) {
		if (content == null) {
			throw new InvalidContentException();
		}
		if (this.type == FileType.IMAGE) {
			throw new WrongFileTypeException();
		}
		for (char ch : content) {
			this.content.add(ch);
		}
    }

	/*
	 * Method to code / test
	 */
    public long getCRC32() {
		if (this.content.isEmpty()) {
			return 0L;
		}

		byte[] bytes = new byte[this.content.size() * 2];
		for (int i = 0; i < this.content.size(); i++) {
			char ch = this.content.get(i);
			int lsb = ch & 0x00FF;
			int msb = (ch >>> 8) & 0x00FF;
			bytes[i * 2] = (byte) lsb;
			bytes[i * 2 + 1] = (byte) msb;
		}

		return this.fileUtils.calculateCRC32(bytes);
    }
    
    
	/*
	 * Setters/getters
	 */
    public void setType(FileType type) {
    	
    	this.type = type;
    	
    }
    
    public List<Character> getContent() {
    	
    	return content;
    	
    }

	void setFileUtils(FileUtils fileUtils) {
		this.fileUtils = fileUtils;
	}
    
}
