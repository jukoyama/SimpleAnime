import java.awt.*;
import java.awt.event.*;

/** ウィンドウの終了でプログラムを終了する
                      ExitableFrame クラス */

public class ExitableFrame extends Frame
                           implements WindowListener {

  /** コンストラクタ */

     public ExitableFrame() {

          super( "Exitable Frame Test" );
          addWindowListener( this );
     }

     public ExitableFrame( String title ) {

          super( title );
          addWindowListener( this );
     }

  /** 起動時に呼び出されるメソッド */

     public static void main( String argv[] ) {

          ExitableFrame frame = new ExitableFrame();
          frame.setSize( 400, 300 );
          frame.setVisible( true );
     }

  /** WindowEvent を処理するメソッド */

     public void windowClosed( WindowEvent evt ){

          System.out.println("window closed");
          System.out.println("system exit");
          System.exit(0);   // システムの終了
     }

     public void windowClosing( WindowEvent evt ){

          System.out.println("window closing");
          dispose();  // フレームの廃棄
     }

     public void windowOpened( WindowEvent evt ){

          System.out.println("window opened");
     }

     public void windowIconified( WindowEvent evt ){

          System.out.println("winodw iconified");
     }

     public void windowDeiconified( WindowEvent evt ){

          System.out.println("window deiconified");
     }

     public void windowActivated( WindowEvent evt ){

          System.out.println("window activated");
     }

     public void windowDeactivated( WindowEvent evt ){

          System.out.println("window deactivated");
     }
}
