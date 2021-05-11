CREATE TABLE IF NOT EXISTS `datanodes` (
  `datanode_uuid` varchar(36) NOT NULL,
  `hostname` varchar(253) NOT NULL,
  `ipaddr` varchar(15) NOT NULL,
  `xfer_port` int(11) NOT NULL,
  `info_port` int(11) NOT NULL,
  `ipc_port` int(11) NOT NULL,
  PRIMARY KEY (`datanode_uuid`)
) ENGINE=ndbcluster DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs
COMMENT='NDB_TABLE=READ_BACKUP=1'