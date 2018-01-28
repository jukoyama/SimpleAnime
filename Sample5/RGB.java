import java.awt.*;
import java.awt.event.*;

/** RGB､ﾎｿｧ､ﾎｺｮｹ遉ｽｼｨ､ｹ､�ｯ･鬣ｹ */

public class RGB extends Panel {

   /** ･ｳ･ｹ･ﾈ･鬣ｯ･ｿ */

      public RGB(){

           super();
           panel = new Panel();
           redPanel = new Panel();
           greenPanel = new Panel();
           bluePanel = new Panel();
           canvas = new Canvas();
           redLabel = new Label("R:");
           greenLabel = new Label("G:");
           blueLabel = new Label("B:");
           redScrollbar = new Scrollbar( Scrollbar.HORIZONTAL,
                                         0, 1, 0, 255 );
           greenScrollbar = new Scrollbar( Scrollbar.HORIZONTAL,
                                         0, 1, 0, 255 );
           blueScrollbar = new Scrollbar( Scrollbar.HORIZONTAL,
                                         0, 1, 0, 255 );
           redConverter = new Converter();
           greenConverter = new Converter();
           blueConverter = new Converter();
           canvas.setSize( 128, 128 );

           redPanel.add( redLabel );
           redPanel.add( redScrollbar );
           redPanel.add( redConverter );
           greenPanel.add( greenLabel );
           greenPanel.add( greenScrollbar );
           greenPanel.add( greenConverter );
           bluePanel.add( blueLabel );
           bluePanel.add( blueScrollbar );
           bluePanel.add( blueConverter );

           panel.setLayout( new GridLayout( 3, 1 ) );
           panel.add( redPanel );
           panel.add( greenPanel );
           panel.add( bluePanel );

           canvas.setBackground( Color.white );
           redScrollbar.setValue( 255 );
           greenScrollbar.setValue( 255 );
           blueScrollbar.setValue( 255 );
           redConverter.setValue( 255 );
           greenConverter.setValue( 255 );
           blueConverter.setValue( 255 );

           setLayout( new BorderLayout() );
           add( canvas, "East" );
           add( panel, "Center" );

           ScrollAdapter scrollAdapter = new ScrollAdapter( this );
           FieldAdapter fieldAdapter = new FieldAdapter( this );

           redScrollbar.addAdjustmentListener( scrollAdapter );
           greenScrollbar.addAdjustmentListener( scrollAdapter );
           blueScrollbar.addAdjustmentListener( scrollAdapter );

           redConverter.addActionListener( fieldAdapter );
           greenConverter.addActionListener( fieldAdapter );
           blueConverter.addActionListener( fieldAdapter );
     }

   /** ･ｭ･罕ﾐ･ｹ､ﾎ･ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈ */

           public Canvas canvas;

   /** ･ﾑ･ﾍ･�ﾎ･ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈ */

           public Panel panel, redPanel, greenPanel, bluePanel;

   /** ･鬣ﾙ･�ﾎ･ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈ */

           public Label redLabel, greenLabel, blueLabel;

   /** ･ｹ･ｯ･悅ｼ･�ﾐ｡ｼ､ﾎ･ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈ */

           public Scrollbar redScrollbar, greenScrollbar, blueScrollbar;

   /** Converter､ﾎ･ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈ(10ｿﾊﾋ｡､ﾈ16ｿﾊﾋ｡､ﾎﾉｽｼｨ) */

           public Converter redConverter, greenConverter, blueConverter;

   /** ｵｯﾆｰｻ､ﾋｸﾆ､ﾓｽﾐ､ｵ､��皈ｽ･ﾃ･ﾉ */

      public static void main( String argv[] ) {

           RGB rgb = new RGB();
           ExitableFrame frame = new ExitableFrame( "RGB Tool" );
           frame.add( rgb, "Center" );
           frame.pack();
           frame.show();
       }

   /** ･ｹ･ｯ･悅ｼ･�ﾐ｡ｼ､ﾎ･｢･ｯ･ｷ･逾靉､ｹ､ inner･ｯ･鬣ｹ */

      class ScrollAdapter implements AdjustmentListener {

         /** ﾁ犲釥ｹ､ RGB･ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈ */

            public RGB rgb;

         /** ･ｳ･ｹ･ﾈ･鬣ｯ･ｿ */

            ScrollAdapter( RGB rgb ){

                  this.rgb = rgb;
            }

         /** ･｢･ｯ･ｷ･逾靉､ﾎ･皈ｽ･ﾃ･ﾉ */

            public void adjustmentValueChanged( AdjustmentEvent evt ) {

                 int r = rgb.redScrollbar.getValue();
                 int g = rgb.greenScrollbar.getValue();
                 int b = rgb.blueScrollbar.getValue();

                 rgb.canvas.setBackground( new Color( r, g, b ) );

                 rgb.redConverter.setValue( r );
                 rgb.greenConverter.setValue( g );
                 rgb.blueConverter.setValue( b );
            }
      } // ScrollAdapter･ｯ･鬣ｹ､ﾎﾄ�ﾁ､ﾎｽｪ､�


   /** ･ﾆ･ｭ･ｹ･ﾈ･ﾕ･｣｡ｼ･�ﾉ､ﾎ･｢･ｯ･ｷ･逾靉､ｹ､ inner･ｯ･鬣ｹ */

      class FieldAdapter implements ActionListener {

         /** ﾁ犲釥ｹ､ RGB･ｪ･ﾖ･ｸ･ｧ･ｯ･ﾈ */

            public RGB rgb;

         /** ･ｳ･ｹ･ﾈ･鬣ｯ･ｿ */

            FieldAdapter( RGB rgb ){

                  this.rgb = rgb;
            }

         /** ･｢･ｯ･ｷ･逾靉､ﾎ･皈ｽ･ﾃ･ﾉ */

            public void actionPerformed( ActionEvent evt ) {

                 int r = redConverter.getValue();
                 int g = greenConverter.getValue();
                 int b = blueConverter.getValue();

                 rgb.canvas.setBackground( new Color( r, g, b ) );

                 rgb.redScrollbar.setValue( r );
                 rgb.greenScrollbar.setValue( g );
                 rgb.blueScrollbar.setValue( b );
            }
      } // FieldAdapter･ｯ･鬣ｹ､ﾎﾄ�ﾁ､ﾎｽｪ､�

} // RGB･ｯ･鬣ｹ､ﾎﾄ�ﾁ､ﾎｽｪ､�
