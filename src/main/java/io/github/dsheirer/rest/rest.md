# Overview
This module implements a REST API for interacting with SDRTrunk. It is hoped
that this may be useful in cases where the user wishes to monitor or tune an 
SDRTrunk installation running headlessly on a remote machine.

# Structure
Modules specifically supporting Spring, specifically, are under
io.github.dsheirer.rest.spring. More general modules are under
io.github.dsheirer.rest.

# Auxiliary modules

## io.github.dsheirer.rest.spring.MessageConverterFilter
This object filters the MappingJackson2Xml converter so that it cannot be used
by Spring. Otherwise, Spring may provide XML serialization when requested by
the client.  For simplicity, I thought it wise to restrict serialization (at
least initially) to JSON only.
