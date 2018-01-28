import java.awt.*;
import java.awt.event.*;

/** 10進数と16進数の変換を行い表示するクラス */

public class Converter extends Panel {

   /** テキストフィールドのオブジェクト(10進法表示用) */

     public TextField decimalField;

   /** テキストフィールドのオブジェクト(16進法表示用) */

     public TextField hexField;

   /** アダプタのオブジェクト */

     protected ConvertAdapter convertAdapter;

   /** 内部に記憶される数値 */

     private int value;

   /** コンストラクタ */

     public Converter(){

          super();  // Panelの生成

          value = 0;
          decimalField = new TextField( "0", 3 );
          hexField = new TextField( "0", 2 );
          add( decimalField );
          add( hexField );

          convertAdapter = new ConvertAdapter( this );
          decimalField.addActionListener( convertAdapter );
          hexField.addActionListener( convertAdapter );
     }

   /** 外部から数値の情報を取り出すためのメソッド */

     public int getValue() {

          return value;
     }

   /** 外部から数値を再設定するためのメソッド */

     public void setValue( int value ) {

          this.value = value;
          setValue10( value );
          setValue16( value );
     }

   /** TextFieldから数値の情報を取り出すためのメソッド */

     protected int getValue10() {

          try{
              value = Integer.parseInt( decimalField.getText() );
          }
          catch( Exception e ){

              setValue( value ); // 以前の値に戻す
          }
          return value;
     }

     protected int getValue16() {

          try{
              value = Integer.parseInt( hexField.getText(), 16 );
          }
          catch( Exception e ){

              setValue( value ); // 以前の値に戻す
          }
          return value;
     }

   /** TextFieldに数値のテキストを設定するためのメソッド */

     protected void setValue10( int value ) {

          decimalField.setText( Integer.toString( value ) );
     }

     protected void setValue16( int value ) {

          hexField.setText( Integer.toHexString( value ) );
     }

   /** ActionListenerの登録を追加するためのメソッド */

     public void addActionListener( ActionListener listener ) {

          decimalField.addActionListener( listener );
          hexField.addActionListener( listener );
     }

   /** ActionListenerの登録を削除するためのメソッド */

     public void removeActionListener( ActionListener listener ) {

          decimalField.removeActionListener( listener );
          hexField.removeActionListener( listener );
     }

   /** TextListenerの登録を追加するためのメソッド */

     public void addTextListener( TextListener listener ) {

          decimalField.addTextListener( listener );
          hexField.addTextListener( listener );
     }

   /** TextListenerの登録を削除するためのメソッド */

     public void removeTextListener( TextListener listener ) {

          decimalField.removeTextListener( listener );
          hexField.removeTextListener( listener );
     }

   /** 単独で起動した時に呼び出されるメソッド */

     public static void main( String argv[] ) {

          Converter converter = new Converter();

          ExitableFrame frame = new ExitableFrame("Converter");
          frame.add( converter, "Center" );
          frame.pack();
          frame.show();
      }

   /** テキストフィールドのアクションを処理する innerクラス */

      class ConvertAdapter implements ActionListener {

         /** 操作する Converterオブジェクト */

            public Converter converter;

         /** コンストラクタ */

            ConvertAdapter( Converter converter ){

                 this.converter = converter;
            }

         /** アクション処理のメソッド */

            public void actionPerformed( ActionEvent evt ) {

                 TextField source = (TextField)evt.getSource();

                 if( source == converter.decimalField ) {

                     int value = converter.getValue10();
                     converter.setValue( value );
                  }
                  else if( source == converter.hexField ) {

                     int value = converter.getValue16();
                     converter.setValue( value );
                 }
            }
      } // ConvertAdapterクラスの定義の終わり
} // Converterクラスの定義の終わり
