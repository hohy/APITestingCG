/*
 * APIViewForm.java
 *
 * Created on 16.10.2011, 16:13:55
 */
package cz.cvut.fit.hybljan2.apitestingcg.view;

import com.sun.tools.javac.tree.JCTree;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.API;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIClass;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIField;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIItem;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIMethod;
import cz.cvut.fit.hybljan2.apitestingcg.apimodel.APIPackage;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Jan Hýbl
 */
public class APIViewForm extends javax.swing.JFrame {

    DefaultTreeModel apiTreeModel;
    private API apiModel;
    
    /** Creates new form APIViewForm */
    public APIViewForm(API apiModel) {
        this.apiModel = apiModel;
        apiTreeModel = new DefaultTreeModel(generateAPITree());
        initComponents();        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        apiTree = new javax.swing.JTree();
        contentScrollPanel = new javax.swing.JScrollPane();
        contentPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        apiTree.setModel(apiTreeModel);
        apiTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                sourceTreeValueChangedHandler(evt);
            }
        });
        jScrollPane1.setViewportView(apiTree);

        contentPanel.setLayout(new javax.swing.BoxLayout(contentPanel, javax.swing.BoxLayout.Y_AXIS));

        jLabel1.setText("<< Select API Item");
        contentPanel.add(jLabel1);

        contentScrollPanel.setViewportView(contentPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contentScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(contentScrollPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sourceTreeValueChangedHandler(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_sourceTreeValueChangedHandler
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) apiTree.getLastSelectedPathComponent();
        if(node == null) return;
        
        APIInfo apiinfo = (APIInfo) node.getUserObject();
        APIItem item = apiinfo.getItem();
        
        contentPanel.removeAll();
        
        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));                        
        
        if(item.getClass().equals(APIPackage.class)) {
            APIPackage pkg = (APIPackage) item;
            // show info from pkg
            contentPanel.add(new JLabel("package"));
            contentPanel.add(nameLabel);
            
        } else if(item.getClass().equals(APIClass.class)) {
            APIClass cls = (APIClass) item;
            contentPanel.add(new JLabel(item.getType().toString()));
            contentPanel.add(nameLabel);
            if(cls.getModifiers() != null) contentPanel.add(new JLabel("Modifiers: " + cls.getModifiers()));
            if(cls.getFields() != null && cls.getFields().size() > 0) {
                contentPanel.add(new JLabel("Fields:"));
                for(APIField f : cls.getFields()) { 
                    contentPanel.add(new JLabel(f.toString()));
                }
            }
        } else if(item.getClass().equals(APIMethod.class)) {
            APIMethod mth = (APIMethod) item;
            contentPanel.add(new JLabel(item.getType().toString()));
            contentPanel.add(nameLabel);
            
            if(mth.getModifiers() != null) contentPanel.add(new JLabel("Modifiers: " + mth.getModifiers()));
            
            if(mth.getParameters() != null && mth.getParameters().size() > 0) {
                contentPanel.add(new JLabel("Parameters:"));
                for(APIField f : mth.getParameters()) { 
                    contentPanel.add(new JLabel(f.toString()));
                }
            }
            
            if(mth.getThrown() != null && mth.getThrown().size() > 0) {
                contentPanel.add(new JLabel("Throws:"));
                for(String s : mth.getThrown()) { 
                    contentPanel.add(new JLabel(s));
                }
            }            
            
            contentPanel.add(new JLabel("Return type: " + mth.getReturnType()));
            
        }
        //contentLabel.setText(item.toString());
        contentPanel.updateUI();
    }//GEN-LAST:event_sourceTreeValueChangedHandler

    private DefaultMutableTreeNode generateAPITree() {
        APIItem api = apiModel;
        APIInfo rootInfo = new APIInfo(apiModel);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootInfo);
        for(APIPackage pkg : apiModel.getPackages()) {
            APIInfo pkgInfo = new APIInfo(pkg);
            DefaultMutableTreeNode pkgNode = new DefaultMutableTreeNode(pkgInfo);
            for(APIClass cls : pkg.getClasses()) {
                APIInfo clsInfo = new APIInfo(cls);
                DefaultMutableTreeNode clsNode = new DefaultMutableTreeNode(clsInfo);
                for(APIMethod mth : cls.getMethods()) {
                    APIInfo mthInfo = new APIInfo(mth);
                    DefaultMutableTreeNode mthNode = new DefaultMutableTreeNode(mthInfo);
                    clsNode.add(mthNode);
                }
                pkgNode.add(clsNode);
            }
            root.add(pkgNode);
        }
        return root;
    }
    
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
            java.util.logging.Logger.getLogger(APIViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(APIViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(APIViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(APIViewForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new APIViewForm(null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree apiTree;
    private javax.swing.JPanel contentPanel;
    private javax.swing.JScrollPane contentScrollPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

class APIInfo {
    private APIItem item;

    public APIInfo(APIItem item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return item.getName();
    }

    public APIItem getItem() {
        return item;
    }
    
    
}