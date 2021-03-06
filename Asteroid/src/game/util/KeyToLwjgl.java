package game.util;

import java.awt.event.KeyEvent;

import org.lwjgl.input.Keyboard;

/*
 * Transforme les event key de lwjgl en key event awt
 */
public class KeyToLwjgl {
	public static int translateKeyCode (String keyCode) {
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_ADD))) return Keyboard.KEY_ADD;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_SUBTRACT))) return Keyboard.KEY_MINUS;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_0))) return Keyboard.KEY_0;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_1))) return Keyboard.KEY_1;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_2))) return Keyboard.KEY_2;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_3))) return Keyboard.KEY_3;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_4))) return Keyboard.KEY_4;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_5))) return Keyboard.KEY_5;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_6))) return Keyboard.KEY_6;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_7))) return Keyboard.KEY_7;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_8))) return Keyboard.KEY_8;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_9))) return Keyboard.KEY_9;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_A))) return Keyboard.KEY_A;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_B))) return Keyboard.KEY_B;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_C))) return Keyboard.KEY_C;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_D))) return Keyboard.KEY_D;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_E))) return Keyboard.KEY_E;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_F))) return Keyboard.KEY_F;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_G))) return Keyboard.KEY_G;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_H))) return Keyboard.KEY_H;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_I))) return Keyboard.KEY_I;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_J))) return Keyboard.KEY_J;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_K))) return Keyboard.KEY_K;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_L))) return Keyboard.KEY_L;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_M))) return Keyboard.KEY_M;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_N))) return Keyboard.KEY_N;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_O))) return Keyboard.KEY_O;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_P))) return Keyboard.KEY_P;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_Q))) return Keyboard.KEY_Q;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_R))) return Keyboard.KEY_R;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_S))) return Keyboard.KEY_S;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_T))) return Keyboard.KEY_T;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_U))) return Keyboard.KEY_U;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_V))) return Keyboard.KEY_V;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_W))) return Keyboard.KEY_W;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_X))) return Keyboard.KEY_X;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_Y))) return Keyboard.KEY_Y;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_Z))) return Keyboard.KEY_Z;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_ALT))) return Keyboard.KEY_LMENU;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_ALT_GRAPH))) return Keyboard.KEY_RMENU;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_BACK_SLASH))) return Keyboard.KEY_BACKSLASH;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_COMMA))) return Keyboard.KEY_COMMA;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_DELETE))) return Keyboard.KEY_DELETE;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_LEFT))) return Keyboard.KEY_LEFT;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_RIGHT))) return Keyboard.KEY_RIGHT;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_UP))) return Keyboard.KEY_UP;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_DOWN))) return Keyboard.KEY_DOWN;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_ENTER))) return Keyboard.KEY_RETURN;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_HOME))) return Keyboard.KEY_HOME;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_MINUS))) return Keyboard.KEY_MINUS;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_PERIOD))) return Keyboard.KEY_PERIOD;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_PLUS))) return Keyboard.KEY_ADD;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_SEMICOLON))) return Keyboard.KEY_SEMICOLON;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_SHIFT))) return Keyboard.KEY_CAPITAL;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_SLASH))) return Keyboard.KEY_SLASH;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_SPACE))) return Keyboard.KEY_SPACE;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_TAB))) return Keyboard.KEY_TAB;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_BACK_SPACE))) return Keyboard.KEY_DELETE;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_CONTROL))) return Keyboard.KEY_LCONTROL;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_ESCAPE))) return Keyboard.KEY_ESCAPE;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_END))) return Keyboard.KEY_END;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_INSERT))) return Keyboard.KEY_INSERT;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_PAGE_UP))) return Keyboard.KEY_PRIOR;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_PAGE_DOWN))) return Keyboard.KEY_NEXT;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_F1))) return Keyboard.KEY_F1;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_F2))) return Keyboard.KEY_F2;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_F3))) return Keyboard.KEY_F3;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_F4))) return Keyboard.KEY_F4;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_F5))) return Keyboard.KEY_F5;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_F6))) return Keyboard.KEY_F6;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_F7))) return Keyboard.KEY_F7;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_F8))) return Keyboard.KEY_F8;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_F9))) return Keyboard.KEY_F9;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_F10))) return Keyboard.KEY_F10;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_F11))) return Keyboard.KEY_F11;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_F12))) return Keyboard.KEY_F12;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_COLON))) return Keyboard.KEY_COLON;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_NUMPAD0))) return Keyboard.KEY_0;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_NUMPAD1))) return Keyboard.KEY_1;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_NUMPAD2))) return Keyboard.KEY_2;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_NUMPAD3))) return Keyboard.KEY_3;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_NUMPAD4))) return Keyboard.KEY_4;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_NUMPAD5))) return Keyboard.KEY_5;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_NUMPAD6))) return Keyboard.KEY_6;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_NUMPAD7))) return Keyboard.KEY_7;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_NUMPAD8))) return Keyboard.KEY_8;
		if (keyCode.equals(KeyEvent.getKeyText(java.awt.event.KeyEvent.VK_NUMPAD9))) return Keyboard.KEY_9;

		return Keyboard.CHAR_NONE;
	}
}
