import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class solve extends JFrame
{

    //фрейм
    static JFrame mFrame;

    public solve(ArrayList arrayList) {


        //создание фрейма
        mFrame = new JFrame("Нэркэс Кульбаева 151-322");
        // добавление закрытия по крестику на верху в углу
        mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // создание плейна с лэйаутом
        JPanel mPlane = new JPanel();
        mPlane.setLayout(new BoxLayout(mPlane, BoxLayout.Y_AXIS));

        // создание скролинга
        JList northList = new JList(arrayList.toArray());
        northList.setLayoutOrientation(JList.VERTICAL);
        northList.setVisibleRowCount(0);


        JScrollPane northScroll = new JScrollPane(northList);
        northScroll.setPreferredSize(new Dimension(100, 100));

        // добавление скролла в плейн
        mPlane.add(northScroll);


        // добавление плейна в фрейм
        mFrame.getContentPane().add(mPlane);

        // утсановка размера окна
        mFrame.setPreferredSize(new Dimension(500, 800));
        // упаковка окна
        mFrame.pack();
        mFrame.setLocationRelativeTo(null);
        mFrame.setVisible(true);
    }

}
