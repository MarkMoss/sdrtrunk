/*
 * *****************************************************************************
 * Copyright (C) 2014-2024 Dennis Sheirer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * ****************************************************************************
 */

package io.github.dsheirer.module.decode.p25.phase1.message.tsbk.standard.osp;

import io.github.dsheirer.bits.CorrectedBinaryMessage;
import io.github.dsheirer.identifier.Identifier;
import io.github.dsheirer.module.decode.p25.identifier.radio.APCO25FullyQualifiedRadioIdentifier;
import io.github.dsheirer.module.decode.p25.identifier.radio.APCO25RadioIdentifier;
import io.github.dsheirer.module.decode.p25.phase1.P25P1DataUnitID;
import io.github.dsheirer.module.decode.p25.phase1.message.tsbk.OSPMessage;
import io.github.dsheirer.module.decode.p25.reference.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit registration response
 */
public class UnitRegistrationResponse extends OSPMessage
{
    private static final int[] RESERVED = {16, 17};
    private static final int[] RESPONSE = {18, 19};
    private static final int[] SYSTEM_ID = {20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private static final int[] SOURCE_ID = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48,
        49, 50, 51, 52, 53, 54, 55};
    private static final int[] SOURCE_ADDRESS = {56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73,
        74, 75, 76, 77, 78, 79};

    private Response mResponse;
    private Identifier mRegisteredRadio;
    private List<Identifier> mIdentifiers;

    /**
     * Constructs a TSBK from the binary message sequence.
     */
    public UnitRegistrationResponse(P25P1DataUnitID dataUnitId, CorrectedBinaryMessage message, int nac, long timestamp)
    {
        super(dataUnitId, message, nac, timestamp);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getMessageStub());
        sb.append(" REGISTRATION ").append(getResponse().name());
        sb.append(" FOR RADIO:").append(getRegisteredRadio());
        return sb.toString();
    }

    public Identifier getRegisteredRadio()
    {
        if(mRegisteredRadio == null)
        {
            int id = getMessage().getInt(SOURCE_ID);
            int localAddress = getMessage().getInt(SOURCE_ADDRESS);

            if(id == localAddress || localAddress == 0)
            {
                mRegisteredRadio = APCO25RadioIdentifier.createTo(id);
            }
            else
            {
                int wacn = 0; //We don't know what the wacn is from this message.
                int systemId = getMessage().getInt(SYSTEM_ID);
                mRegisteredRadio = APCO25FullyQualifiedRadioIdentifier.createTo(localAddress, wacn, systemId, id);
            }
        }

        return mRegisteredRadio;
    }

    public Response getResponse()
    {
        if(mResponse == null)
        {
            mResponse = Response.fromValue(getMessage().getInt(RESPONSE));
        }

        return mResponse;
    }

    @Override
    public List<Identifier> getIdentifiers()
    {
        if(mIdentifiers == null)
        {
            mIdentifiers = new ArrayList<>();
            mIdentifiers.add(getRegisteredRadio());
        }

        return mIdentifiers;
    }
}
