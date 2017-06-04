package br.com.courier.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;

import br.com.courier.dominio.Cliente;
import br.com.courier.dominio.Encomenda;
import br.com.courier.persistencia.ClienteDB;
import br.com.courier.persistencia.EncomendaDB;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroEncomendaUI extends JFrame {

	private JPanel contentPane;
	private JTextField textData;
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroEncomendaUI frame = new CadastroEncomendaUI();
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
	
	//atributos
		private int tipo;
		private Encomenda encomenda;
		private JTextField textFrete;
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
	
	public CadastroEncomendaUI() {
		setTitle("Encomenda");
		
		//inicializando os atributos
		this.tipo = tipo;
		this.encomenda = encomenda;
		
		setBounds(100, 100, 736, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, this.tipo == 0 ? "Cadastrar Encomenda" : "Alterar Encomenda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		final JComboBox cbxRem = new JComboBox();
		ClienteDB clienteDBR = new ClienteDB();
		List<Cliente> clientesR = clienteDBR.buscarTodos();
		DefaultComboBoxModel comboModelR = (DefaultComboBoxModel) cbxRem.getModel();
		comboModelR.removeAllElements();
		Iterator<Cliente> itR = clientesR.iterator();
		Cliente r;
		while (itR.hasNext()){
			r = itR.next();
			comboModelR.addElement(r);
		}
		
		
		final JComboBox cbxDest = new JComboBox();
		ClienteDB clienteDBD = new ClienteDB();
		List<Cliente> clientesD = clienteDBD.buscarTodos();
		DefaultComboBoxModel comboModelD = (DefaultComboBoxModel) cbxDest.getModel();
		comboModelD.removeAllElements();
		Iterator<Cliente> itD = clientesD.iterator();
		Cliente d;
		while (itD.hasNext()){
			d = itD.next();
			comboModelD.addElement(d);
		}
		
		
		final JCheckBox chkbxPago = new JCheckBox("Pago");
		
		final JTextArea textDesc = new JTextArea();
		
		
		
				
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EncomendaDB encomendaDB = new EncomendaDB();
				DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date data;
				try{
					data = (Date) formatter.parse(textData.getText());
					if (CadastroEncomendaUI.this.tipo == 0){
						//inserir uma encomenda
						encomendaDB.inserir(new Encomenda((Cliente) (cbxRem.getSelectedItem()), 
								(Cliente) (cbxDest.getSelectedItem()), data, null, textDesc.getText(), 
								textFrete.getText(), chkbxPago.isSelected()));
					}else{
						//alterar uma encomenda
						CadastroEncomendaUI.this.encomenda.setClienteD((Cliente) (cbxRem.getSelectedItem()));
						CadastroEncomendaUI.this.encomenda.setClienteD((Cliente) (cbxDest.getSelectedItem()));
						CadastroEncomendaUI.this.encomenda.setDataEntrada(data);
						CadastroEncomendaUI.this.encomenda.setFrete(textFrete.getText());
						CadastroEncomendaUI.this.encomenda.setStatPgto(chkbxPago.isSelected());
						CadastroEncomendaUI.this.encomenda.setDescr(textDesc.getText());
						
					}
				}catch (ParseException pe){
					pe.getMessage();
					pe.printStackTrace();
				}
				setVisible(false);

			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnSalvar)
							.addGap(18)
							.addComponent(btnCancelar)
							.addGap(8)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblRemetente = new JLabel("Remetente:");
		
		JLabel lblDestinatrio = new JLabel("Destinat\u00E1rio:");
		
		
		
		
		
		JLabel lblDataDeEntrada = new JLabel("Data de Entrada");
		
		textData = new JTextField();
		textData.setColumns(10);
		
		JLabel lblDescrioDoPacote = new JLabel("Descri\u00E7\u00E3o do pacote:");
		
		JLabel lblFrete = new JLabel("Frete");
		
		
		
		textFrete = new JTextField();
		textFrete.setColumns(10);
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDestinatrio)
								.addComponent(lblRemetente))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(cbxRem, 0, 527, Short.MAX_VALUE)
								.addComponent(cbxDest, 0, 527, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblDataDeEntrada, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(textData, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
									.addComponent(lblFrete)
									.addComponent(textFrete, 0, 0, Short.MAX_VALUE))
								.addComponent(chkbxPago))
							.addGap(48)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textDesc, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
								.addComponent(lblDescrioDoPacote))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(22)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRemetente)
						.addComponent(cbxRem, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDestinatrio)
						.addComponent(cbxDest, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataDeEntrada)
						.addComponent(lblDescrioDoPacote))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblFrete)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFrete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chkbxPago))
						.addComponent(textDesc, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	
		if (this.tipo == 1) {
			cbxRem.setSelectedItem(clienteDBR.buscarPorID(this.encomenda.getId()));
			cbxDest.setSelectedItem(clienteDBD.buscarPorID(this.encomenda.getId()));
			textData.setText(formatter.format(this.encomenda.getDataEntrada().getTime()));
			textFrete.setText(this.encomenda.getFrete());
			chkbxPago.setSelected(this.encomenda.isStatPgto());
			textDesc.setText(this.encomenda.getDescr());			
			}
	
	}
	
}
