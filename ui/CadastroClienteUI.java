package br.com.courier.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import br.com.courier.dominio.Cliente;
import br.com.courier.persistencia.ClienteDB;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroClienteUI extends JFrame {

	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textCpf;
	private int tipo;
	private Cliente cliente;
	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroClienteUI frame = new CadastroClienteUI();
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
	public CadastroClienteUI(int tipo, Cliente cliente) {
		this.tipo = tipo;
		this.cliente = cliente;
		setTitle("Cliente");
		setBounds(100, 100, 556, 306);
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		
		String titulo;
		if (this.tipo == 0){
			titulo = "Cadastrar Cliente";
		} else {
			titulo = "Alterar Cliente";
			textNome.setText(this.cliente.getNome());
			textCpf.setText(this.cliente.getCpf());
		}
		
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNomeCompleto = new JLabel("Nome completo");
		
		textNome = new JTextField();
		textNome.setColumns(10);
		
		JLabel lblCpfapenasNmeros = new JLabel("CPF (apenas n\u00FAmeros)");
		
		textCpf = new JTextField();
		textCpf.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteDB clienteDB = new ClienteDB();
				if (CadastroClienteUI.this.tipo == 0){
					clienteDB.inserir(new Cliente(textNome.getText(), textCpf.getText()));
				}else{
					CadastroClienteUI.this.cliente.setNome(textNome.getText());
					CadastroClienteUI.this.cliente.setCpf(textCpf.getText());
					clienteDB.modificar(CadastroClienteUI.this.cliente);
				}setVisible(false);
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(textNome, GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
						.addComponent(lblNomeCompleto)
						.addComponent(lblCpfapenasNmeros)
						.addComponent(textCpf, GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(btnSalvar)
							.addGap(18)
							.addComponent(btnCancelar)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNomeCompleto)
					.addGap(1)
					.addComponent(textNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblCpfapenasNmeros)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textCpf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancelar)
						.addComponent(btnSalvar))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
