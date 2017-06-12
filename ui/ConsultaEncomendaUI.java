package br.com.courier.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.com.courier.persistencia.ConsultasDB;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsultaEncomendaUI extends JFrame {

	private JPanel contentPane;
	private JTextField textDest;
	private JButton btnEntregar;
	private JTable tbConsultaEncomenda;
	
	private DefaultTableModel modelo = new DefaultTableModel();
	private List<Object[]> lista;

	
	/**
	 * Create the frame.
	 */
	public ConsultaEncomendaUI() {
		
		setTitle("Consulta Encomendas");
		setBounds(100, 100, 860, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btnEntregar = new JButton("Entregar");
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(720)
					.addComponent(btnEntregar)
					.addGap(15))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnEntregar))
		);
		
		String[] nomesColuna = {"Destinatário", "Descrição do Pacote", "Status de pgto"};
		modelo.setColumnIdentifiers(nomesColuna);
		JTable table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		JLabel lblDestinatrio = new JLabel("Destinat\u00E1rio:");
		
		textDest = new JTextField();
		textDest.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultasDB consultasDB = new ConsultasDB();
				lista = consultasDB.consultaEmprestimos(textDest.getText());
				Iterator<Object[]> it = lista.iterator();
				Object[] linha;
				while (modelo.getRowCount() > 0) {
					modelo.removeRow(0);
				}
				while (it.hasNext()) {
					linha = it.next();
					modelo.addRow(linha);
				}
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDestinatrio)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textDest, GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnPesquisar)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDestinatrio)
						.addComponent(textDest, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPesquisar))
					.addContainerGap(143, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
