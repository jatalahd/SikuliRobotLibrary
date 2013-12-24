package org.robotframework.sikulirobotlibrary;

import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.RobotKeywordOverload;
import org.sikuli.basics.Settings;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.script.Location;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Button;


@RobotKeywords
public class SikuliKeywords {

    private double waitTimeout = 15.0D;
    private double waitAfterAction = 0.0D;
    private Screen scr;
    private Match latestMatch;

    /* Constructor with initializers */
    public SikuliKeywords() {
        scr = new Screen();
        // forcing the OCR text recognition on
        Settings.OcrTextSearch=true;
        Settings.OcrTextRead=true;
    }
    
    /* Customised Exception handling class */
    private class NotFoundError extends Exception {  
        public NotFoundError(String msg) {
            super(msg);
        }
    }
    
    /* Definitions for some selected special keys */
    private enum KeyPressCodes {
        ENTER,
        BACKSPACE,
        TAB,
        ESC,
        ARROW_UP, ARROW_DOWN, ARROW_RIGHT, ARROW_LEFT,
        PAGE_UP, PAGE_DOWN,
        DELETE,
        END,
        HOME,
        INSERT,
        SHIFT,
        CTRL,
        ALT,
        F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, F11, F12;
    }
   
    /* Definitions for some selected special key combinations */
    private enum KeyPressCombinationCodes {
        CTRL_C, CTRL_V, CTRL_A, CTRL_X,
        CTRL_ALT_DELETE, CTRL_SHIFT_ENTER,
        ALT_F4, ALT_TAB;
    }
   
    private Location getScreenLocation(String object) throws Exception {
        Thread.sleep( (int)(this.waitAfterAction * 1000) );
	this.latestMatch = scr.wait(object, this.waitTimeout);
	return this.latestMatch.getTarget();
    }
    
    @RobotKeyword("Sets a timeout value, which is used for waiting objects (images or text) to become visible on the page. "
                   + "Very useful when navigating across different applications and waiting for some timed event to occur. "
                   + "Suggested usage would reset this value before each test case. Default internal value is currently 15.0 seconds. "
                   + "The argument is given in seconds, that is, the argument 60.0 waits a minute before giving a timeout.\n\n"
                   + "Example:\n"
                   + "| SetFindObjectTimeout | 30.0 |\n")
    @ArgumentNames({"timeout"})
    public void setFindObjectTimeout(final String timeout) {
        this.waitTimeout = Double.parseDouble(timeout);
    }

    @RobotKeyword("Sets a waiting time, which is used to slow down the keyword execution. "
                   + "When given a value 3.0 as argument, each keyword is executed in three second intervals.\n\n"
                   + "Example:\n"
                   + "| SetWaitAfterAction | 5.0 |\n")
    @ArgumentNames({"wait"})
    public void setWaitAfterAction(final String wait) {
        this.waitAfterAction = Double.parseDouble(wait);
    }
        
    @RobotKeyword("Executes a left mouse button click on the indicated object given as argument. "
                   + "If the argument is a path to a image file, bitmap comparison is used, "
                   + "otherwise the argument is taken as text and OCR is used for identification. "
                   + "Objects are waited to be found until the timeout set by the 'SetFindObjectTimeout' keyword expires.\n\n"
                   + "Examples:\n"
                   + "| Click | C:\\path_to_imagefile\\template.png |\n"
                   + "| Click | Some text |\n")
    @ArgumentNames({"object"})
    public void click(final String object) throws NotFoundError {
        try {
	    scr.click( getScreenLocation(object) );
	} catch(Exception e) {
            throw new NotFoundError("Error: Could not locate object - " + object + " - on screen.");
        }
    }

    @RobotKeyword("Does a left mouse button doubleclick on the indicated object given as argument. "
                   + "If the argument is a path to a image file, bitmap comparison is used, "
                   + "otherwise the argument is taken as text and OCR is used for identification. "
                   + "Objects are waited to be found until the timeout set by the 'SetFindObjectTimeout' keyword expires.\n\n"
                   + "Examples:\n"
                   + "| DoubleClick | C:\\path_to_imagefile\\template.png |\n"
                   + "| DoubleClick | Some text |\n")
    @ArgumentNames({"object"})
    public void doubleClick(final String object) throws NotFoundError {
        try {
            scr.doubleClick( getScreenLocation(object) );
	} catch(Exception e) {
            throw new NotFoundError("Error: Could not locate object - " + object + " - on screen.");
        }
    }
        
    @RobotKeyword("Executes a right mouse button click on the indicated object given as argument. "
                   + "If the argument is a path to a image file, bitmap comparison is used, "
                   + "otherwise the argument is taken as text and OCR is used for identification. "
                   + "Objects are waited to be found until the timeout set by the 'SetFindObjectTimeout' keyword expires.\n\n"
                   + "Examples:\n"
                   + "| RightClick | C:\\path_to_imagefile\\template.png |\n"
                   + "| RightClick | Some text |\n")
    @ArgumentNames({"object"})
    public void rightClick(final String object) throws NotFoundError {
        try {
            scr.rightClick( getScreenLocation(object) );
	} catch(Exception e) {
            throw new NotFoundError("Error: Could not locate object - " + object + " - on screen.");
        }
    }

    @RobotKeyword("Executes a left mouse button click on pixel-offset measured from the center of the object given as argument. "
                   + "In pixel-coordinates the top-left corner of the screen is the origin (0,0). "
                   + "If the object argument is a path to a image file, bitmap comparison is used, "
                   + "otherwise the argument is taken as text and OCR is used for identification. "
                   + "Objects are waited to be found until the timeout set by the 'SetFindObjectTimeout' keyword expires.\n\n"
                   + "Examples:\n"
                   + "| ClickWithOffset | C:\\path_to_imagefile\\template.png | 100 | -60 |\n"
                   + "| ClickWithOffset | Some text | -20 | 20 |\n")
    @ArgumentNames({"object","x","y"})
    public void clickWithOffset(final String object, final String x, final String y) throws NotFoundError {
        try {
            Location obj = getScreenLocation( object );
            Location offset = new Location( obj.getX() + Integer.parseInt(x), obj.getY() + Integer.parseInt(y) );
	    scr.click( offset );
	} catch(Exception e) {
            throw new NotFoundError("Error: Could not locate object - " + object + " - on screen.");
        }
    }

    @RobotKeyword("Does a left mouse button double click on pixel-offset measured from the center of the object given as argument. "
                   + "In pixel-coordinates the top-left corner of the screen is the origin (0,0). "
                   + "If the object argument is a path to a image file, bitmap comparison is used, "
                   + "otherwise the argument is taken as text and OCR is used for identification. "
                   + "Objects are waited to be found until the timeout set by the 'SetFindObjectTimeout' keyword expires.\n\n"
                   + "Examples:\n"
                   + "| DoubleClickWithOffset | C:\\path_to_imagefile\\template.png | 100 | -60 |\n"
                   + "| DoubleClickWithOffset | Some text | -20 | 20 |\n")
    @ArgumentNames({"object","x","y"})
    public void doubleClickWithOffset(final String object, final String x, final String y) throws NotFoundError {
        try {
            Location obj = getScreenLocation( object );
            Location offset = new Location( obj.getX() + Integer.parseInt(x), obj.getY() + Integer.parseInt(y) );
            scr.doubleClick( offset );
	} catch(Exception e) {
            throw new NotFoundError("Error: Could not locate object - " + object + " - on screen.");
        }
    }
        
    @RobotKeyword("Executes a right mouse button click on pixel-offset measured from the center of the object given as argument. "
                   + "In pixel-coordinates the top-left corner of the screen is the origin (0,0). "
                   + "If the object argument is a path to a image file, bitmap comparison is used, "
                   + "otherwise the argument is taken as text and OCR is used for identification. "
                   + "Objects are waited to be found until the timeout set by the 'SetFindObjectTimeout' keyword expires.\n\n"
                   + "Examples:\n"
                   + "| RightClickWithOffset | C:\\path_to_imagefile\\template.png | 100 | -60 |\n"
                   + "| RightClickWithOffset | Some text | -20 | 20 |\n")
    @ArgumentNames({"object","x","y"})
    public void rightClickWithOffset(final String object, final String x, final String y) throws NotFoundError {
        try {
            Location obj = getScreenLocation( object );
            Location offset = new Location( obj.getX() + Integer.parseInt(x), obj.getY() + Integer.parseInt(y) );
            scr.rightClick( offset );
	} catch(Exception e) {
            throw new NotFoundError("Error: Could not locate object - " + object + " - on screen.");
        }
    }

    @RobotKeyword("Simulates a mouse driven drag and drop action. Both the moved object and the target object need to be given as arguments. "
                   + "If the argument is a path to a image file, bitmap comparison is used, "
                   + "otherwise the argument is taken as text and OCR is used for identification. "
                   + "Objects are waited to be found until the timeout set by the 'SetFindObjectTimeout' keyword expires.\n\n"
                   + "Examples:\n"
                   + "| DragAndDrop | C:\\path_to_imagefile\\template.png |\n"
                   + "| DragAndDrop | Some text |\n")
    @ArgumentNames({"fromObject","toObject"})
    public void dragAndDrop(final String fromObject, final String toObject) throws NotFoundError {
        try {
         int result = scr.dragDrop( getScreenLocation(fromObject), getScreenLocation(toObject) );
	} catch(Exception e) {
            throw new NotFoundError("Error: Could not locate object - " + fromObject + " - or - " + toObject + " -on screen.");
        }
    }
       
   @RobotKeyword("Moves the mouse curson on top the object given as argument, thereby simulating a hover action. "
                   + "Hovered objects are waited to be found until the timeout set by the 'SetFindObjectTimeout' keyword expires.\n\n"
                   + "Examples:\n"
                   + "| HoverOnObject | C:\\path_to_imagefile\\template.png |\n"
                   + "| HoverOnObject | Some text |\n")
    @ArgumentNames({"object"})
    public void hoverOnObject(final String object) throws NotFoundError {
        try {
            scr.mouseMove( getScreenLocation(object) );
	} catch(Exception e) {
            throw new NotFoundError("Error: Could not locate object - " + object + " - on screen.");
        }
    }
    
    @RobotKeyword("Presses down the left mouse button on the current cursor position "
                   + "and keeps it pressed until the 'ReleaseMouseButtons' keyword is called.\n\n"
                   + "Example:\n"
                   + "| PressLeftMouseButtonDown |\n")
    public void pressLeftMouseButtonDown() throws Exception {
        Thread.sleep( (int)(this.waitAfterAction * 1000) );
        scr.mouseDown( Button.LEFT );
    }
    
    @RobotKeyword("Releases the mouse button if it is currently being pressed by the 'PressLeftMouseButtonDown' keyword.\n\n"
                   + "Example:\n"
                   + "| ReleaseMouseButtons |\n")
    public void releaseMouseButtons() throws Exception {
        Thread.sleep( (int)(this.waitAfterAction * 1000) );
        scr.mouseUp();
    }

    @RobotKeyword("Does a left mouse button click on the indicated object given as argument. "
                   + "If the argument is a path to a image file, bitmap comparison is used, "
                   + "otherwise the argument is taken as text and OCR is used for identification. "
                   + "The narrowed region to search the object should be indicated by giving the "
                   + "pixel-coordinates of the top-left corner followed by region width and height. "
                   + "The top-left corner of the screen is the origin with coordinate (0,0). "
                   + "Objects are waited to be found until the timeout set by the 'SetFindObjectTimeout' keyword expires.\n\n"
                   + "Examples:\n"
                   + "| ClickInRegion | C:\\path_to_imagefile\\template.png | 100 | 100 | 200 | 300 |\n"
                   + "| ClickInRegion | Some text | 100 | 100 | 200 | 300 |\n")
    @ArgumentNames({"object", "x", "y", "width", "height"})
    public void clickInRegion(String object, String x, String y, String width, String height) throws NotFoundError {
        try {
            int a = Integer.parseInt(x);
            int b = Integer.parseInt(y);
            int c = Integer.parseInt(width);
            int d = Integer.parseInt(height);
	    Region rg = new Region(a, b, c, d);
            rg.click( rg.wait(object, waitTimeout) );
        }
        catch(Exception e){
            throw new NotFoundError("Error: Could not locate object - " + object + " - on screen");
        }
    }

    @RobotKeyword("Does a left mouse button doubleclick on the indicated object given as argument. "
                   + "If the argument is a path to a image file, bitmap comparison is used, "
                   + "otherwise the argument is taken as text and OCR is used for identification. "
                   + "The narrowed region to search the object should be indicated by giving the "
                   + "pixel-coordinates of the top-left corner followed by region width and height. "
                   + "The top-left corner of the screen is the origin with coordinate (0,0). "
                   + "Objects are waited to be found until the timeout set by the 'SetFindObjectTimeout' keyword expires.\n\n"
                   + "Examples:\n"
                   + "| DoubleClickInRegion | C:\\path_to_imagefile\\template.png | 100 | 100 | 200 | 300 |\n"
                   + "| DoubleClickInRegion | Some text | 100 | 100 | 200 | 300 |\n")
    @ArgumentNames({"object", "x", "y", "width", "height"})
    public void doubleClickInRegion(String object, String x, String y, String width, String height) throws NotFoundError {
        try {
            int a = Integer.parseInt(x);
            int b = Integer.parseInt(y);
            int c = Integer.parseInt(width);
            int d = Integer.parseInt(height);
	    Region rg = new Region(a, b, c, d);
            rg.doubleClick( rg.wait(object, waitTimeout) );
        }
        catch(Exception e){
            throw new NotFoundError("Error: Could not locate object - " + object + " - on screen");
        }
    }
    
    @RobotKeyword("Does a right mouse button click on the indicated object given as argument. "
                   + "If the argument is a path to a image file, bitmap comparison is used, "
                   + "otherwise the argument is taken as text and OCR is used for identification. "
                   + "The narrowed region to search the object should be indicated by giving the "
                   + "pixel-coordinates of the top-left corner followed by region width and height. "
                   + "The top-left corner of the screen is the origin with coordinate (0,0). "
                   + "Objects are waited to be found until the timeout set by the 'SetFindObjectTimeout' keyword expires.\n\n"
                   + "Examples:\n"
                   + "| RightClickInRegion | C:\\path_to_imagefile\\template.png | 100 | 100 | 200 | 300 |\n"
                   + "| RightClickInRegion | Some text | 100 | 100 | 200 | 300 |\n")
    @ArgumentNames({"object", "x", "y", "width", "height"})
    public void rightClickInRegion(String object, String x, String y, String width, String height) throws NotFoundError {
        try {
            int a = Integer.parseInt(x);
            int b = Integer.parseInt(y);
            int c = Integer.parseInt(width);
            int d = Integer.parseInt(height);
	    Region rg = new Region(a, b, c, d);
            rg.rightClick( rg.wait(object, waitTimeout) );
        }
        catch(Exception e){
            throw new NotFoundError("Error: Could not locate object - " + object + " - on screen");
        }
    }
    
    @RobotKeyword("Executes a left mouse button click at the pixel-coordinate given as argument. "
                   + "The top-left corner of the screen is the origin with coordinate (0,0). \n\n"
                   + "Example:\n"
                   + "| ClickOnCoordinate | 100 | 100 |\n")
    @ArgumentNames({"x", "y"})
    public void clickOnCoordinate(final String x, final String y) throws Exception {
        Thread.sleep( (int)(this.waitAfterAction * 1000) );
        scr.click( new Location(Integer.parseInt(x), Integer.parseInt(y)) );
    }
    
    @RobotKeyword("Executes a left mouse button doubleclick at the pixel-coordinate given as argument. "
                   + "The top-left corner of the screen is the origin with coordinate (0,0). \n\n"
                   + "Example:\n"
                   + "| DoubleClickOnCoordinate | 100 | 100 |\n")
    @ArgumentNames({"x", "y"})
    public void doubleClickOnCoordinate(final String x, final String y) throws Exception {
        Thread.sleep( (int)(this.waitAfterAction * 1000) );
        scr.doubleClick( new Location(Integer.parseInt(x), Integer.parseInt(y)) );
    }
    
    @RobotKeyword("Executes a right mouse button click at the pixel-coordinate given as argument. "
                   + "The top-left corner of the screen is the origin with coordinate (0,0). \n\n"
                   + "Example:\n"
                   + "| RightClickOnCoordinate | 100 | 100 |\n")
    @ArgumentNames({"x", "y"})
    public void rightClickOnCoordinate(final String x, final String y) throws Exception {
        Thread.sleep( (int)(this.waitAfterAction * 1000) );
        scr.rightClick( new Location(Integer.parseInt(x), Integer.parseInt(y)) );
    }
    
    @RobotKeyword("Writes the text given as argument to the current carret position.\n\n"
                   + "Example:\n"
                   + "| TypeText | Some Text |\n")
    @ArgumentNames({"text"})
    public void typeText(final String text) throws Exception {
        Thread.sleep( (int)(this.waitAfterAction * 1000) );
        scr.type(text);
    }
    
    @RobotKeyword("Presses a special key from the keyboard. "
                   + "The currently supported keys are: ENTER, BACKSPACE, TAB, ESC, ARROW_UP, ARROW_DOWN,  "
		   + "ARROW_RIGHT, ARROW_LEFT, PAGE_UP, PAGE_DOWN, DELETE, END, HOME, INSERT, SHIFT, CTRL, ALT, "
		   + "and F1 - F12. All the keys might not work, and the status of the numlock affects the functionality.\n\n"
                   + "Example:\n"
                   + "| PressKey | ARROW_UP |\n")
    @ArgumentNames({"keyCode"})
    public void pressKey(final String keyCode) throws Exception {
        Thread.sleep( (int)(this.waitAfterAction * 1000) );
        KeyPressCodes cds = KeyPressCodes.valueOf(keyCode);
        switch( cds ) {
            case ENTER:       scr.type( Key.ENTER );     break;
            case BACKSPACE:   scr.type( Key.BACKSPACE ); break;
            case TAB:         scr.type( Key.TAB );       break;
            case ESC:         scr.type( Key.ESC );       break;
            case ARROW_UP:    scr.type( Key.UP );        break;
            case ARROW_DOWN:  scr.type( Key.DOWN );      break;
            case ARROW_RIGHT: scr.type( Key.RIGHT );     break;
            case ARROW_LEFT:  scr.type( Key.LEFT );      break;
            case PAGE_UP:     scr.type( Key.PAGE_UP );   break;
            case PAGE_DOWN:   scr.type( Key.PAGE_DOWN ); break;
            case DELETE:      scr.type( Key.DELETE );    break;
            case END:         scr.type( Key.END );       break;
            case HOME:        scr.type( Key.HOME );      break;
            case INSERT:      scr.type( Key.INSERT );    break;
            case SHIFT:       scr.type( Key.SHIFT );     break;
            case CTRL:        scr.type( Key.CTRL );      break;
            case ALT:         scr.type( Key.ALT );       break;
            case F1:          scr.type( Key.F1 );        break;
            case F2:          scr.type( Key.F2 );        break;
            case F3:          scr.type( Key.F3 );        break;
            case F4:          scr.type( Key.F4 );        break;
            case F5:          scr.type( Key.F5 );        break;
            case F6:          scr.type( Key.F6 );        break;
            case F7:          scr.type( Key.F7 );        break;
            case F8:          scr.type( Key.F8 );        break;
            case F9:          scr.type( Key.F9 );        break;
            case F10:         scr.type( Key.F10 );       break;
            case F11:         scr.type( Key.F11 );       break;
            case F12:         scr.type( Key.F12 );       break;
            default: break;
        }
    }
    
    @RobotKeyword("Presses a special key combination from the keyboard.\n\n"
                   + "Examples:\n"
                   + "| PressKeyCombination | ALT+TAB |\n"
                   + "| PressKeyCombination | CTRL+A |\n"
		   + "| PressKeyCombination | CTRL+C |\n"
		   + "| PressKeyCombination | CTRL+V |\n"
                   + "| PressKeyCombination | CTRL+X |\n"
                   + "| PressKeyCombination | CTRL+ALT+DELETE |\n")
    @ArgumentNames({"keyCodeCombination"})
    public void pressKeyCombination(final String keyCodeCombination) throws Exception {
        Thread.sleep( (int)(this.waitAfterAction * 1000) );
        String modKeyCode = keyCodeCombination.replaceAll("\\+","_");
        KeyPressCombinationCodes cds = KeyPressCombinationCodes.valueOf(modKeyCode);
        switch( cds ) {
            case ALT_TAB:         scr.type( Key.TAB, KeyModifier.ALT );     break;
	    case CTRL_A:          scr.type( "a", KeyModifier.CTRL );     break;
            case CTRL_C:          scr.type( "c", KeyModifier.CTRL );     break;
            case CTRL_V:          scr.type( "v", KeyModifier.CTRL );     break;
	    case CTRL_X:          scr.type( "x", KeyModifier.CTRL );     break;
	    case CTRL_ALT_DELETE: scr.type( Key.DELETE, KeyModifier.CTRL + KeyModifier.ALT );     break;
            default: break;
        }
    }
    
    @RobotKeyword("Waits until the given object (image or text) appears on screen or the timeout expires. "
                   + "The timeout parameter is optional and if not given the internal 'findObjectTimeout' is used (see SetFindObjectTimeout). "
		   + "When specified, the timeout parameter should be given in seconds, e.g. 10.5  , look for more examples below.\n\n"
                   + "Examples:\n"
                   + "| WaitForObjectToAppear | C:\\path_to_image\\template.png | 12.0 |\n"
		   + "| WaitForObjectToAppear | C:\\path_to_image\\template.png |\n"
		   + "| WaitForObjectToAppear | Some Text | 9.5 |\n"
                   + "| WaitForObjectToAppear | Some Text |\n")
    @ArgumentNames({"object","timeout="})
    public void waitForObjectToAppear(final String object, final String timeout) throws NotFoundError {
        try {
            Match result = scr.wait( object, Double.parseDouble(timeout) );
        } catch(Exception e) {
            throw new NotFoundError("Error: Object - " + object + " - did not appear within timeout " + timeout);
        }
    }
    
    @RobotKeywordOverload
    public void waitForObjectToAppear(final String object) throws NotFoundError {
        try {
            Match result = scr.wait(object, waitTimeout);
        } catch(Exception e) {
            throw new NotFoundError("Error: Object - " + object + " - did not appear within timeout " + waitTimeout);
        }
    }
    
    @RobotKeyword("Waits until the given object (image or text) disappears on screen or the timeout expires. "
                   + "The timeout parameter is optional and if not given the internal 'findObjectTimeout' is used (see SetFindObjectTimeout). "
		   + "When specified, the timeout parameter should be given in seconds, e.g. 10.5  , look for more examples below.\n\n"
                   + "Examples:\n"
                   + "| WaitForObjectToDisappear | C:\\path_to_image\\template.png | 12.0 |\n"
		   + "| WaitForObjectToDisappear | C:\\path_to_image\\template.png |\n"
		   + "| WaitForObjectToDisappear | Some Text | 9.5 |\n"
                   + "| WaitForObjectToDisappear | Some Text |\n")
    @ArgumentNames({"object","timeout="})
    public void waitForObjectToDisappear(final String object, final String timeout) throws NotFoundError {
        Boolean result = scr.waitVanish( object, Double.parseDouble(timeout) );
        if (!result) {
             throw new NotFoundError("Error: Object - " + object + " - did not disappear within timeout " + timeout);
        }
    }
    
    @RobotKeywordOverload
    public void waitForObjectToDisappear(final String object) throws NotFoundError {
        Boolean result = scr.waitVanish(object, waitTimeout);
        if (!result) {
             throw new NotFoundError("Error: Object - " + object + " - did not disappear within timeout " + waitTimeout);
        }
    }
    
    @RobotKeyword("This keyword can be used for testing that a text or an image object is found on screen. "
                   + "If the object is found, the obtained similarity score (0.0 - 1.0) is printed to the log. "
		   + "The internal 'findObjectTimeout' is used (see SetFindObjectTimeout) to specify the time for waiting the object to appear.\n\n" 
                   + "Examples:\n"
                   + "| Find | C:\\path_to_image\\template.png |\n"
                   + "| Find | Some Text |\n")
    @ArgumentNames({"object"})
    public void find(final String object) throws NotFoundError {
        try {
            Match result = scr.wait(object, waitTimeout);
            if(result != null) {
                System.out.println("Found object with similarity score: " + result.getScore() );
            }
        }
        catch(FindFailed e){
            throw new NotFoundError("Error: Could not locate object - " + object + " - on screen");
        }
    }
    
    @RobotKeyword("LocateText keyword can be used for verifying that given text exists on the screen. "
                   + "The internal 'findObjectTimeout' is used (see SetFindObjectTimeout) to specify the time for waiting the object to appear.\n\n"
                   + "Example:\n"
                   + "| LocateText | Some Text |\n")
    @ArgumentNames({"text"})
    public void locateText(final String text) throws NotFoundError {
        Match result = scr.exists(text, waitTimeout);
        if (result == null) {
             throw new NotFoundError("Error: Could not locate text - " + text + " - on screen");
        }
    }
    
    @RobotKeyword("LocateImage keyword can be used to verify that "
                   + "the given template image exists on screen with a specified similarity score (0.00 - 1.00). "
                   + "The similarity score parameter is optional, and if not given, the Sikuli default 0.70 is used. "
		   + "The internal 'findObjectTimeout' is used (see SetFindObjectTimeout) to specify the time for waiting the object to appear.\n\n"
                   + "Example:\n"
                   + "| LocateImage | C:\\path_to_image\\template.png | 0.97 |\n")
    @ArgumentNames({"img","similarity="})
    public void locateImage(final String img, final String similarity) throws NotFoundError {
        Match result = scr.exists(img, waitTimeout);
        if ( result == null || result.getScore() < Float.parseFloat(similarity) ) {
             throw new NotFoundError("Error: Could not locate image - " + img + " - on screen");
        }
    }
    
    @RobotKeywordOverload
    public void locateImage(final String img) throws NotFoundError {
        Match result = scr.exists(img, waitTimeout);
        if ( result == null || result.getScore() < Settings.MinSimilarity ) {
             throw new NotFoundError("Error: Could not locate image - " + img + " - on screen");
        }
    }
    
} // End Of SikuliKeywords
