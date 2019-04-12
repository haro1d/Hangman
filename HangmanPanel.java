import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class HangmanPanel extends JPanel {
 
 public HangmanPanel(){
  lexicon = new HangmanLexicon();
  initWord();
  initMessage();
  
  setPreferredSize(new Dimension(800,600));
  setLayout(new GridLayout(1,2));
  leftPanel = new InputPanel();
  displayPanel = new HangmanDisplayPanel();
  add(leftPanel);
  add(displayPanel);
  
 }
      //�������Ҫ�µĵ���
 private void initWord(){
  Random generator=new Random();
  int index = generator.nextInt(lexicon.getWordCount());
     try{
      word = lexicon.getWord(index);
  }catch(Exception e){
   e.printStackTrace();
  }
  wordDisplay = new StringBuffer("");
  for(int i = 0;i<word.length();i++){
   wordDisplay.append("��");
   
  }
  
 }
      //��ʼ��Ҫ��ʾ����ʾ��Ϣ
 private void initMessage(){
  guessNumLeft = 8;
  message = "Welcome to Hangman!\n";
  message += word+"\n";
  message += "The word now looks like this:";
  message += wordDisplay;
  message += "\n";
  message += "You have " + guessNumLeft + " guess left\n";
 }
 
 private class InputPanel extends JPanel{
  public InputPanel(){
   setPreferredSize(new Dimension(400,600));
   setBorder(BorderFactory.createLoweredBevelBorder());
   setLayout(new FlowLayout());
   
   messageTextArea = new JTextArea(message);
   
   messageTextArea.setEditable(false);
   messageTextArea.setAutoscrolls(true);
   
   JScrollPane scroll = new JScrollPane(messageTextArea);
   scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
   scroll.setPreferredSize(new Dimension(380,560));
   add(scroll);
   
   JLabel hint = new JLabel("Your guess:");
   input = new JTextField();
   confirm = new JButton("OK");
   restart = new JButton("Restart");
   confirm.addActionListener(new ConfirmListener());
   restart.addActionListener(new RestartListener());
   JPanel bottom = new JPanel();
   bottom.setLayout(new GridLayout(1,4));
   bottom.add(hint);
   bottom.add(input);
   bottom.add(confirm);
   bottom.add(restart);
   
   add(bottom);
  }
  /* (non-Javadoc)
   * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
   */
  @Override
  protected void paintComponent(Graphics g) {
   
   super.paintComponent(g);//ÿ���ػ���ʾ��Ϣ����
   messageTextArea.setText(message);
   messageTextArea.setCaretPosition(message.length());
   //ÿ���ػ棬������ÿգ�����ȡ����
   input.setText("");
   input.requestFocus();
  }
 }
 
 private class ConfirmListener implements ActionListener{
  @Override
  public void actionPerformed(ActionEvent event) {
   String guess = input.getText();
   message += "You guess is "+guess+"\n";
   
   if(guess.length()==1){//�û��������һ���ַ�
    int indexFound = word.indexOf(guess.toUpperCase());
    
    if(indexFound != -1){//������-1�����ҵ�
     message += "The guess is correct\n";
     wordDisplay.setCharAt(indexFound, guess.toUpperCase().charAt(0));
     //�û�������ַ������µ����г��ֶ�ε����
     indexFound = word.indexOf(guess.toUpperCase(),indexFound+1);
     while(indexFound!=-1){
      wordDisplay.setCharAt(indexFound, guess.toUpperCase().charAt(0));
      indexFound = word.indexOf(guess.toUpperCase(),indexFound+1);
     }
    }else{//-1����û���ҵ�
     message +="There are no "+guess+"'s in the word.\n";
     guessNumLeft--;
     //֪ͨ�ұ�panel����
     displayPanel.noteIncorrectGuess(8-guessNumLeft);
     if(guessNumLeft == 0){
      message += "You are completely hung!\n";
      message += "The word are "+word+"\n";
      message += "You lose\n";
      input.setEditable(false);
      confirm.setEnabled(false);
     }
    
    }
   }else{//���������ݳ��ȷ�1
    message += "The guess is illegal\n";
   }
   if(wordDisplay.indexOf("����")==-1){//ȫ���¶�
    message+= "You guess the word "+word+"\n";
    message+= "You win";
    input.setEditable(false);
    confirm.setEnabled(false);
   }else if(guessNumLeft !=0){
    message += "The word now looks like this:";
    message += wordDisplay;
    message += "\n";
    message += "You have " + guessNumLeft + " guess left\n";
   }
    repaint();
  }
 }
 
 private class RestartListener implements ActionListener{
  @Override
  public void actionPerformed(ActionEvent e) {
   
   initWord();
   initMessage();
   input.setEditable(true);
   confirm.setEnabled(true);
   displayPanel.reset();
   repaint();
  }
  
 }
 
 private InputPanel leftPanel;
 private HangmanDisplayPanel displayPanel;
 
 private JTextArea messageTextArea;//��ʾ��ʾ��Ϣ
 private JTextField input;         //�����ı���
 private JButton confirm;          //OK��ť
 private JButton restart;          //���¿�ʼ��ť
 private String message;           //�洢��ʾ��Ϣ������
 private int guessNumLeft;         //ʣ��²����
 private HangmanLexicon lexicon;   //�ֵ�
 private String word;              //Ҫ�µĵ���
 private StringBuffer wordDisplay; //������ʾ��ʽ
 
}
//3.HangmanDisplayPanel
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import javax.swing.JPanel;

/*
 * File: HangmanDisplayPanel.java
 * ------------------------
 * ���ļ�������ұߵ�hangman������ʾ.
 */

