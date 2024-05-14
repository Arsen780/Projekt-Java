import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RegistrationForm extends JDialog {
    private JTextField tfimie;
    private JTextField tfnazwisko;
    private JTextField tfnazwa;
    private JTextField tfemail;
    private JPasswordField pfhaslo;
    private JPasswordField pfpotwierdzhaslo;
    private JButton zarejestrujSięButton;
    private JButton anulujButton;
    private JPanel rejestracja;

    public RegistrationForm(JFrame parent){
        super(parent);
        setTitle("Załóż nowe konto");
        setContentPane(rejestracja);
        setMinimumSize(new Dimension(500,550));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        zarejestrujSięButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rejestracja_uzytkownika();
            }
        });
        anulujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anuluj();
            }
        });

        setVisible(true);
    }

    private void anuluj() {
        setVisible(false);
        dispose();
    }

    private void rejestracja_uzytkownika() {
        String imie = tfimie.getText();
        String nazwisko = tfnazwisko.getText();
        String nazwa = tfnazwa.getText();
        String email = tfemail.getText();
        String haslo = String.valueOf(pfhaslo.getPassword());
        String potwierdzhaslo = String.valueOf(pfpotwierdzhaslo.getPassword());

        if(imie.isEmpty() || nazwisko.isEmpty() || email.isEmpty() || nazwa.isEmpty() || haslo.isEmpty() || potwierdzhaslo.isEmpty()){
            JOptionPane.showMessageDialog(this, "Proszę wypełnić wszystkie pola!", "Spróbuj ponownie", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(!haslo.equals(potwierdzhaslo)){
            JOptionPane.showMessageDialog(this, "Hasła nie są takie same!", "Spróbuj ponownie", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(nazwa.length()<6){
            JOptionPane.showMessageDialog(this, "Nazwa użytkownika powinna zawierać conajmniej 6 znaków", "Spróbuj ponownie", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int duzelitery = 0;
        int malelitery = 0;
        int cyfry = 0;
        int malpa = 0;
        boolean duza = false;
        boolean mala = false;
        boolean cyfra = false;
        boolean malpaznak = false;
        char[] tablicahaslo = haslo.toCharArray();

        for(char znak : tablicahaslo){
            if(Character.isUpperCase(znak)){
                duzelitery++;
            } else if (Character.isLowerCase(znak)){
                malelitery++;
            } else if (Character.isDigit(znak)) {
                cyfry++;
            }
        }

        char[] tablicaemail = email.toCharArray();

        for(char znak1 : tablicaemail){
            if(znak1 == '@'){
                malpa++;
            }
        }

        if (duzelitery>0) duza = true;
        if(malelitery>0) mala =true;
        if(cyfry>0) cyfra = true;
        if(malpa == 1) malpaznak = true;

        if(haslo.length()<8 || cyfra == false || mala == false || duza == false){
            JOptionPane.showMessageDialog(this, "Hasło powinno zawierać conajmniej 8 znaków, małe i duże litery oraz cyfry", "Spróbuj ponownie", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if(malpaznak == false){
            JOptionPane.showMessageDialog(this, "Nieprawidłowy adres email!", "Spróbuj ponownie", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = addUserToDatabase(imie,nazwisko,nazwa,email,haslo);
        if(user != null){
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(this,"NIe udało się zarejestrować nowego użtkownika!", "Spróbuj ponownie", JOptionPane.ERROR_MESSAGE);
        }
    }

    public User user;
    private User addUserToDatabase(String imie, String nazwisko, String nazwa, String email, String haslo){
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/rejestracja?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            Statement stmt = conn.createStatement();// tworzenie obiektu do polecen sql
            String sql = "INSERT INTO users (imie, nazwisko, email, nazwa,haslo)"+ "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);// przygotowuje zapytania dla sql
            preparedStatement.setString(1, imie); // ustawia wartosc imie do 1 adresu
            preparedStatement.setString(2,nazwisko);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,nazwa);
            preparedStatement.setString(5, haslo);

            int addedRows = preparedStatement.executeUpdate();
            if(addedRows>0){
                user = new User();
                user.imie = imie;
                user.nazwisko = nazwisko;
                user.nazwa = nazwa;
                user.email = email;
                user.haslo = haslo;
            }

            stmt.close();
            conn.close();

        }catch(Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static void main(String[] args){
        RegistrationForm myForm = new RegistrationForm(null);

    }
}