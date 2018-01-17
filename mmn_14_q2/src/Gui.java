import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Gui extends JPanel {
    // dimensions
    private static final int windowWidth = 400;
    private static final int windowHeight = 290;
    private static final int dateWidth = windowWidth;
    private static final int dateHeight = 50;
    private static final int contentWidth = windowWidth;
    private static final int contentHeight = 150;
    private static final int buttonsWidth = windowWidth;
    private static final int buttonsHeight = 30;

    // GUI elements
    // date pickers
    private JComboBox<Integer> day, month, year;
    // content area
    private JTextArea taContent;
    // action tfStatus
    private JTextField tfStatus;

    // storage data structure
    private ReminderStorage reminderStorage;

    /**
     * C'tor
     * @param filePath to data-file
     */
    public Gui(String filePath) {
        super();
        // layout for elements placement
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // set date pickers
        JPanel pnlDatePick = new JPanel(new GridLayout(1, 3));
        pnlDatePick.setPreferredSize(new Dimension(dateWidth, dateHeight));

        day = picker(1, 31);
        month = picker(1, 12);
        year = picker(2017, 2117);

        pnlDatePick.add(wrap("Day",day ));
        pnlDatePick.add(wrap("Month", month));
        pnlDatePick.add(wrap("Year", year));
        add(pnlDatePick);

        // set content area
        JPanel pnlContent = new JPanel(new GridLayout(1, 1));
        pnlContent.setPreferredSize(new Dimension(contentWidth, contentHeight));
        
        taContent = new JTextArea();
        taContent.setMinimumSize(new Dimension(contentWidth, contentHeight));
        pnlContent.add(taContent);
        add(pnlContent);

        // set buttons
        JPanel pnlButtons = new JPanel(new GridLayout(1, 2));
        pnlButtons.setPreferredSize(new Dimension(buttonsWidth, buttonsHeight));

        /// show button
        JButton btnShow = new JButton("Show");
        btnShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showRmd();
            }
        });

        // update button
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Date date = parseDate();
                    Reminder reminder = new Reminder(date, taContent.getText());
                    reminderStorage.saveReminder(reminder);
                    tfStatus.setText("Reminder for " + date + " has been updated!");
                }
                catch (InvalidDateException e) {
                    tfStatus.setText(e.getMessage() + ", ignoring update");
                }
            }
        });

        pnlButtons.add(btnShow);
        pnlButtons.add(btnUpdate);
        add(pnlButtons);

        // set status
        tfStatus = new JTextField();
        tfStatus.setEditable(false);
        tfStatus.setText("Please make an action");
        add(tfStatus);

        reminderStorage = new ReminderStorage(filePath);
    }

    /**
     * Inits all GUI units
     */
    public void init() {
        JFrame frame = new JFrame("Reminders");
        frame.add(this);
        frame.setSize(windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // show first reminder by default
        showRmd();
    }

    /**
     * Sets reminder's content text
     */
    public void showRmd() {
        try {
            Date date = parseDate();
            String reminder = reminderStorage.loadReminder(date);
            taContent.setText(reminder);
            tfStatus.setText("Please make an action");
        }
        catch (InvalidDateException e) {
            tfStatus.setText(e.getMessage() + ", ignoring operation");
        }
    }

    /**
     * @param minValue to put in picker
     * @param maxValue to put in picker
     * @return picker combo box
     */
    private JComboBox<Integer> picker(int minValue, int maxValue) {
        JComboBox<Integer> box = new JComboBox<>();

        for (int item = minValue; item <= maxValue; item++) {
            box.addItem(item);
        }

        box.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie == null || ItemEvent.SELECTED != ie.getStateChange()) {
                    return;
                }
                // change made, load again
                showRmd();
            }
        });
        return box;
    }

    /**
     * @param name for label
     * @param box to wrap
     * @return
     */
    private JPanel wrap(String name, JComboBox<Integer> box) {
        JPanel picker = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel(name);
        picker.add(label);
        picker.add(box);
        return picker;
    }

    /**
     * @return Date instance created pickers
     * @throws InvalidDateException
     */
    private Date parseDate() throws InvalidDateException {
        Object dayItem = day.getSelectedItem();
        Object monthItem = month.getSelectedItem();
        Object yearItem = year.getSelectedItem();

        if (!(dayItem instanceof Integer) ||
            !(monthItem instanceof Integer) ||
            !(yearItem instanceof Integer)) {
            throw new InvalidDateException("Invalid types");
        }
        return new Date((Integer)dayItem, (Integer)monthItem, (Integer)yearItem);
    }
}