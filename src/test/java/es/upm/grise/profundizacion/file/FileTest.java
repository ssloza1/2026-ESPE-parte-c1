package es.upm.grise.profundizacion.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class FileTest {
	
	@Test
    public void smokeTest() {}

    @Test
    public void constructorInitializesEmptyContent() {
        File file = new File();

        List<Character> content = file.getContent();
        assertNotNull(content);
        assertEquals(0, content.size());
    }

    @Test
    public void addPropertyNullThrowsInvalidContentException() {
        File file = new File();

        assertThrows(InvalidContentException.class, () -> file.addProperty(null));
    }

    @Test
    public void addPropertyWithImageTypeThrowsWrongFileTypeException() {
        File file = new File();
        file.setType(FileType.IMAGE);

        assertThrows(WrongFileTypeException.class, () -> file.addProperty(new char[] {'a'}));
    }

    @Test
    public void addPropertyAppendsToExistingContent() {
        File file = new File();
        file.setType(FileType.PROPERTY);

        file.addProperty(new char[] {'k', '=', 'v'});
        file.addProperty(new char[] {'1'});

        List<Character> content = file.getContent();
        assertEquals(4, content.size());
        assertEquals(Character.valueOf('k'), content.get(0));
        assertEquals(Character.valueOf('='), content.get(1));
        assertEquals(Character.valueOf('v'), content.get(2));
        assertEquals(Character.valueOf('1'), content.get(3));
    }

    @Test
    public void getCRC32EmptyContentReturnsZero() {
        File file = new File();

        assertEquals(0L, file.getCRC32());
    }

    @Test
    public void getCRC32ReturnsValueFromFileUtils() {
        File file = new File();
        file.setType(FileType.PROPERTY);
        file.addProperty(new char[] {'A'});

        FileUtils utils = new FileUtils();
        utils.setCRC(987654321L);
        file.setFileUtils(utils);

        assertEquals(987654321L, file.getCRC32());
    }

}
