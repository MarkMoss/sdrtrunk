/*******************************************************************************
 *     SDR Trunk 
 *     Copyright (C) 2014 Dennis Sheirer
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>
 ******************************************************************************/
package module.decode.p25;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import module.decode.DecodeEditor;
import module.decode.config.DecodeConfiguration;
import module.decode.p25.P25Decoder.Modulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import source.SourceEditor;
import source.tuner.TunerEditor;
import controller.Editor;
import controller.channel.ChannelValidationException;

public class P25Editor extends DecodeEditor
{
	private final static Logger mLog = LoggerFactory.getLogger( P25Editor.class );

	private static final long serialVersionUID = 1L;
    
    private JComboBox<P25_LSMDecoder.Modulation> mComboModulation;
    private JLabel mCallTimeoutLabel;
    private JSlider mCallTimeout;
    private JLabel mTrafficChannelPoolSizeLabel;
    private JSlider mTrafficChannelPoolSize;

    public P25Editor( DecodeConfiguration config )
	{
		super( config );
		
		initGUI();
	}

	private void initGUI()
	{
		mComboModulation = new JComboBox<P25_LSMDecoder.Modulation>();

		mComboModulation.setModel( new DefaultComboBoxModel<P25_LSMDecoder.Modulation>( 
				P25_LSMDecoder.Modulation.values() ) );
		
		mComboModulation.setSelectedItem( ((DecodeConfigP25Phase1)mConfig).getModulation() );
		
		add( new JLabel( "Modulation:" ) );
		add( mComboModulation, "wrap" );
		
		mCallTimeout = new JSlider( JSlider.HORIZONTAL,
				DecodeConfiguration.CALL_TIMEOUT_MINIMUM,
				DecodeConfiguration.CALL_TIMEOUT_MAXIMUM,
				DecodeConfiguration.DEFAULT_CALL_TIMEOUT_SECONDS );

		mCallTimeout.setMajorTickSpacing( 90 );
		mCallTimeout.setMinorTickSpacing( 10 );
		mCallTimeout.setPaintTicks( true );
		
		mCallTimeout.setLabelTable( mCallTimeout.createStandardLabels( 100, 100 ) );
		mCallTimeout.setPaintLabels( true );
		
		mCallTimeoutLabel = new JLabel( "Call Timeout: " + mCallTimeout.getValue() + " " );
		
		mCallTimeout.addChangeListener( new ChangeListener()
		{
			@Override
			public void stateChanged( ChangeEvent e )
			{
				mCallTimeoutLabel.setText( "Call Timeout: " + mCallTimeout.getValue() );
			}
		} );
		
		add( mCallTimeoutLabel );
		add( mCallTimeout, "wrap,grow" );
		
		mTrafficChannelPoolSize = new JSlider( JSlider.HORIZONTAL,
				DecodeConfiguration.TRAFFIC_CHANNEL_LIMIT_MINIMUM,
				DecodeConfiguration.TRAFFIC_CHANNEL_LIMIT_MAXIMUM,
				DecodeConfiguration.TRAFFIC_CHANNEL_LIMIT_DEFAULT );
		
		mTrafficChannelPoolSize.setMajorTickSpacing( 10 );
		mTrafficChannelPoolSize.setMinorTickSpacing( 1 );
		mTrafficChannelPoolSize.setPaintTicks( true );
		
		mTrafficChannelPoolSize.setLabelTable( mTrafficChannelPoolSize.createStandardLabels( 10, 10 ) );
		mTrafficChannelPoolSize.setPaintLabels( true );
		
		mTrafficChannelPoolSizeLabel = new JLabel( "Traffic Channel Pool Size: " + mTrafficChannelPoolSize.getValue() + " " );
		
		mTrafficChannelPoolSize.addChangeListener( new ChangeListener()
		{
			@Override
			public void stateChanged( ChangeEvent e )
			{
				mTrafficChannelPoolSizeLabel.setText( "Traffic Channel Pool Size: " + mTrafficChannelPoolSize.getValue() );
			}
		} );
		
		add( mTrafficChannelPoolSizeLabel );
		add( mTrafficChannelPoolSize, "wrap,grow" );
		
		reset();
	}

	/**
	 * Enforce source=tuner for CQPSK modulation
	 */
	@Override
    public void validate( Editor editor ) throws ChannelValidationException
    {
		if( editor instanceof SourceEditor && 
			((DecodeConfigP25Phase1)mConfig).getModulation() == Modulation.CQPSK )
		{
			if( !( editor instanceof TunerEditor ) )
			{
				throw new ChannelValidationException( 
						"<html><body width='175'><h1>LSM Simulcast</h1>"
						+ "<p>P25 LSM Simulcast decoder can only be used with "
						+ "a tuner source.  Please change the Source to use a tuner"
						+ " or change the P25 Decoder to C4FM modulation" );
				
			}
		}
    }
	
	@Override
    public void save()
	{
		DecodeConfigP25Phase1 config = (DecodeConfigP25Phase1)mConfig;

		config.setModulation( (Modulation)mComboModulation.getSelectedItem() );
		
		config.setCallTimeout( mCallTimeout.getValue() );
		
		config.setTrafficChannelPoolSize( mTrafficChannelPoolSize.getValue() );
    }

	@Override
    public void reset()
    {
		DecodeConfigP25Phase1 config = (DecodeConfigP25Phase1)mConfig;

		mComboModulation.setSelectedItem( config.getModulation() );

		mCallTimeout.setValue( config.getCallTimeout() );
		
		mTrafficChannelPoolSize.setValue( config.getTrafficChannelPoolSize() );
    }
}
