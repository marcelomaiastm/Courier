package br.com.courier.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;

import br.com.courier.dominio.Cliente;
import br.com.courier.persistencia.ClienteDB;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClienteUI extends JFrame {

	private JPanel contentPane;
	private JTable tbCliente;
	private DefaultTableModel modelo = new DefaultTableModel();
	private List<Cliente> lista;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteUI frame = new ClienteUI();
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
	public ClienteUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				atualizaTabela();
			}
		});
		setTitle("Cliente");
		setBounds(100, 100, 863, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroClienteUI cadastroCliente = new CadastroClienteUI(0, null);
				cadastroCliente.setVisible(true);
			}
		});
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tbCliente.getSelectedRow() != -1){
					CadastroClienteUI cadastroCliente = new CadastroClienteUI(1, lista.get(tbCliente.getSelectedRow()));
					cadastroCliente.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "Selecione um registro da tabela :p");
				}
			}
		});
		
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tbCliente.getSelectedRow() != -1){
					ClienteDB clienteDB = new ClienteDB();
					clienteDB.excluir(lista.get(tbCliente.getSelectedRow()));
				}else{
					JOptionPane.showMessageDialog(null, "Selecione um registro da tabela :p");
				}
			}
		});
		
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				atualizaTabela();
			}
		});
				
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(btnAtualizar)
							.addGap(18)
							.addComponent(btnNovo)
							.addGap(18)
							.addComponent(btnAlterar)
							.addGap(18)
							.addComponent(btnRemover)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemover)
						.addComponent(btnAlterar)
						.addComponent(btnNovo)
						.addComponent(btnAtualizar))
					.addGap(18))
		);
		
		String[] nomesColuna = {"Código", "Nome"};
		modelo.setColumnIdentifiers(nomesColuna);
		tbCliente = new JTable(modelo);
		scrollPane.setViewportView(tbCliente);
		contentPane.setLayout(gl_contentPane);
		
				
	}
	
	private void atualizaTabela(){
		ClienteDB clienteDB = new ClienteDB();
		lista = clienteDB.buscarTodos();
		Iterator<Cliente> it = lista.iterator();
		Cliente a;
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		while (it.hasNext()) {
			a = it.next();
			modelo.addRow(new Object[] {a.getId(), a.getNome()});
		}
	}
	
	
	
	
}
