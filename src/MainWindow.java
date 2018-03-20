import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Map;

public class MainWindow {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField searchTextField;
    private JTextArea resultTextArea;
    private JButton searchButton;
    private JCheckBox exactMatchCheckBox;
    private JLabel movieCountLabel;
    private JLabel ratingCountLabel;

    public MainWindow() {
        SimpleMovieSearchEngine s = new SimpleMovieSearchEngine();
        s.loadData("data-sample/movies.csv", "data-sample/ratings.csv");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSearchResult(s);
            }
        });
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                updateSearchResult(s);
            }
        });
        exactMatchCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSearchResult(s);
            }
        });
    }

    public void updateSearchResult(SimpleMovieSearchEngine s){
        int numRatings = 0;
        resultTextArea.setText("");

        LinkedList<Movie> searchedList = (LinkedList<Movie>) s.searchByTitle(searchTextField.getText(), exactMatchCheckBox.isSelected());

        for(Movie movie: searchedList){
            resultTextArea.append(movie + "\n");
            numRatings += movie.getRating().size();
        }
        movieCountLabel.setText("Movie Count: " + searchedList.size());
        ratingCountLabel.setText("Rating Count: " + numRatings);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Moogle");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MainWindow().panel1);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(0, 0, 960, 540);

    }
}
