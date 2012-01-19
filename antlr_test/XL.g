grammar XL;

WS
	:
	(	' '
	|	'\t'
	|	'\f'

	// handle newlines
	|	( '\r\n'  // DOS/Windows
		|	'\r'    // Macintosh
		|	'\n'    // Unix
		)
		// increment the line count in the scanner
		{ newline(); }
	)
	{$setType(Token.SKIP); }
	;
	
COMMENT
	:	'//' (~('\n'|'\r'))*
		{ $setType(Token.SKIP); }
  ;

// multiline comments
ML_COMMENT
	:	'/*'
		(
			options {
			}
			: {LA(2) != '/'}
		)*
		'*/'
	;