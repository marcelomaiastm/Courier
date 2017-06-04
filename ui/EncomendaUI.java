package br.com.courier.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;

import br.com.courier.dominio.Encomenda;
import br.com.courier.persistencia.EncomendaDB;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EncomendaUI extends JFrame {

	private JPanel contentPane;
	private JTable tbEncomenda;
	private DefaultTableModel modelo = new DefaultTableModel();
	private List<Encomenda> lista;
	
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncomendaUI frame = new EncomendaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public EncomendaUI() {
		setTitle("Encomenda");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				atualizaTabela();
			}
		});
		setBounds(100, 100, 711, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnRemover = new JButton("Remover");
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tbEncomenda.getSelectedRow() != -1) {
					if (JOptionPane.showConfirmDialog(null, "Confirma a exclusão do registro?", "Exclusão",
							JOptionPane.YES_NO_OPTION) == 0) {
					EncomendaDB encomendaDB = new EncomendaDB();
					encomendaDB.excluir(lista.get(tbEncomenda.getSelectedRow()));
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um registro na tabela");
				}
			}
		});
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroEncomendaUI cadastroEncomenda = new CadastroEncomendaUI();
				cadastroEncomenda.setVisible(true);
			}
		});
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizaTabela();
			}
		});
		
		JButton btnEntregar = new JButton("Entregar");
		btnEntregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tbEncomenda.getSelectedRow() != -1) {
					if (JOptionPane.showConfirmDialog(null, "Confirma a devolução do livro?", "Devolução",
							JOptionPane.YES_NO_OPTION) == 0) {
						EncomendaDB encomendaDB = new EncomendaDB();
						Encomenda encomenda = lista.get(tbEncomenda.getSelectedRow());
						encomenda.setDataSaida(Calendar.getInstance().getTime());
						encomendaDB.modificar(encomenda);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um registro na tabela");
				}
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnAtualizar)
							.addGap(18)
							.addComponent(btnNovo)
							.addGap(18)
							.addComponent(btnAlterar)
							.addGap(18)
							.addComponent(btnRemover)
							.addGap(18)
							.addComponent(btnEntregar)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEntregar)
						.addComponent(btnRemover)
						.addComponent(btnAlterar)
						.addComponent(btnNovo)
						.addComponent(btnAtualizar))
					.addContainerGap())
		);
		
		
		String[] nomesColuna = {"Destinatário", "Descrição do pacote"};
		modelo.setColumnIdentifiers(nomesColuna);
		
		tbEncomenda = new JTable(modelo);
		scrollPane.setViewportView(tbEncomenda);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void atualizaTabela(){
		EncomendaDB encomendaDB = new EncomendaDB();
		lista = encomendaDB.buscarNaoEntregues();
		Iterator<Encomenda> it = lista.iterator();
		Encomenda e;
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		while (it.hasNext()) {
			e = it.next();
			modelo.addRow(new Object[] {e.getClienteD(), e.getDescr()});
		}
	}
	
}
