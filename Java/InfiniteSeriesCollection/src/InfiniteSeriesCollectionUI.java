/**
 * InfiniteSeriesCollection.java - Lab3
 * Author: Cody Lewis
 * Last Modified: 02-04-2017
 * Description:
 * Collection of infinite sum programs made in lab 3
 */
import javax.swing.*;
import java.util.*;

public class InfiniteSeriesCollectionUI extends javax.swing.JDialog {

    /**
     * Creates new form InfiniteSeriesCollectionUI
     */
    public InfiniteSeriesCollectionUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        mainPanel = new javax.swing.JPanel();
        AddTwoSeries = new javax.swing.JButton();
        FactorialSum = new javax.swing.JButton();
        DivisionSquareSeries = new javax.swing.JButton();
        MultiplicationSeries = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Choose your equation"));

        AddTwoSeries.setText("2+4+6+...+n");
        AddTwoSeries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddTwoSeriesActionPerformed(evt);
            }
        });

        FactorialSum.setText("1! - 3! + 5! - ... + n!");
        FactorialSum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FactorialSumActionPerformed(evt);
            }
        });

        DivisionSquareSeries.setText("(1/2)^2+(2/3)^2+(3/4)^2+...+(n-1/n)^2");
        DivisionSquareSeries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DivisionSquareSeriesActionPerformed(evt);
            }
        });

        MultiplicationSeries.setText("1x3 + 2x4 + 3x5 + ... + n(n+2)");
        MultiplicationSeries.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MultiplicationSeriesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(AddTwoSeries, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(FactorialSum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(DivisionSquareSeries, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MultiplicationSeries, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddTwoSeries)
                    .addComponent(DivisionSquareSeries))
                .addGap(29, 29, 29)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MultiplicationSeries)
                    .addComponent(FactorialSum))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitBtn)
                    .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitBtn)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void AddTwoSeriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddTwoSeriesActionPerformed
        Scanner console = new Scanner(System.in);
		int numberIn,series,numberOut;
		
		series = -2;
		numberOut = 0;
		numberIn = Integer.parseInt(JOptionPane.showInputDialog("Please enter your number: "));

		if((numberIn/2)*2 == numberIn){
			do{
					
				series = series + 2;
				numberOut = numberOut + series;
			}while(series < numberIn);
		}
		else{
			do{

				series = series + 2;
				numberOut = numberOut + series;
			}while(series < (numberIn - 1));
		}

		JOptionPane.showMessageDialog(null,"The infinite sum incrementing by 2 up to " + numberIn + " is " + numberOut);

    }//GEN-LAST:event_AddTwoSeriesActionPerformed

    private void DivisionSquareSeriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DivisionSquareSeriesActionPerformed
        Scanner console = new Scanner(System.in);
		double numberIn,series,numberOut;

		numberOut = 0;
		series = 1;
		numberIn = Integer.parseInt(JOptionPane.showInputDialog("Please enter your number: "));
		
		do{
			series = series + 1;
			numberOut = numberOut + (((series-1)/(series))*((series-1)/(series)));
		}while(series<numberIn);

		JOptionPane.showMessageDialog(null,"The infinite sum incrementing by 1 where a fraction is squared,\n up to the number " + numberIn + " is " + numberOut);
    }//GEN-LAST:event_DivisionSquareSeriesActionPerformed

    private void FactorialSumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FactorialSumActionPerformed
        Scanner console = new Scanner(System.in);
		int numberIn,factorialCount,factorial,series,numberOut,seriesCount;	// Declares integers for the input, two for the factorial and one for the output
		factorial = 1;
		numberOut = 0;
		int seriesTrack = 1;	// Tracks whether the factorial will be added or subtracted

		numberIn = Integer.parseInt(JOptionPane.showInputDialog("Please enter your number"));

		// Creates a nested loop which manages the creation of the series involve a factorial the initial if-else statement checks whether the input number is odd or even
		if(numberIn%2 == 0){
			for (series = 1; series < numberIn+1; series+=2){
				for(factorialCount = 1; factorialCount<series+1; factorialCount++){
					factorial = factorial * factorialCount;
				}
			if (seriesTrack%2 == 0){
				numberOut = numberOut - factorial;
			}
			else{
				numberOut = numberOut + factorial;
			}
			seriesTrack++;
			factorial = 1;
		 }
		}
		else{
			for (series = 1; series < numberIn+1; series+=2){
				for(factorialCount = 1; factorialCount<series+1; factorialCount++){
					factorial = factorial * factorialCount;
				}
			if (seriesTrack%2 == 0){
				numberOut = numberOut - factorial;
			}
			else{
				numberOut = numberOut + factorial;
			}
			seriesTrack++;
			factorial = 1;
		 }
		}
		// Output the data then exits when the user is ready
		JOptionPane.showMessageDialog(null,"The sum of the series holding the equation of n! up to " + numberIn + " is " + numberOut);
    }//GEN-LAST:event_FactorialSumActionPerformed

    private void MultiplicationSeriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MultiplicationSeriesActionPerformed
        		Scanner console = new Scanner(System.in);
		int numberIn,series,numberOut;
		
		series = 0;
		numberOut = 0;

		numberIn = Integer.parseInt(JOptionPane.showInputDialog("Please enter your number:"));

		if((numberIn/2)*2 == numberIn){
			do{
				series = series + 1;
				numberOut = numberOut + (series * (series + 2));
			  }while(series < numberIn);
		}
		else{
			do{
				
				series = series + 1;
				numberOut = numberOut + (series * (series + 2));
			}while(series < (numberIn-1));
		}

			JOptionPane.showMessageDialog(null,"The sum of the infinite addition series of n(n+2)\n up to " + numberIn + " is " + numberOut);
    }//GEN-LAST:event_MultiplicationSeriesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InfiniteSeriesCollectionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InfiniteSeriesCollectionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InfiniteSeriesCollectionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InfiniteSeriesCollectionUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InfiniteSeriesCollectionUI dialog = new InfiniteSeriesCollectionUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddTwoSeries;
    private javax.swing.JButton DivisionSquareSeries;
    private javax.swing.JButton FactorialSum;
    private javax.swing.JButton MultiplicationSeries;
    private javax.swing.JButton exitBtn;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
