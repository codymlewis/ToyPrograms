/* Simplified Diffie-Hellman Key Exchange */
/* Author: Cody Lewis - c3283349 */
/* Date: 2018-08-12 */

/* Program Information */

{
	help() =
		print("Diffie-Hellman Key Exchange");
		print;
		print("Enter:");
		print("- setP(P)			: to set the prime number P");
		print("- setAlpha(a)		: to set the primitive element a");
		print("- setPubKeyA(pubKey)	: to set Alice's public key");
		print("- setPubKeyB(pubKey)	: to set Bob's public key");
		print("- setPubKeyE(pubKey)	: to set Eve's public key");
		print("- setPriKeyA(priKey)	: to set Alice's private key");
		print("- setPriKeyB(priKey)	: to set Bob's private key");
		print("- setPriKeyE(priKey)	: to set Eve's private key");
		print;
		print("Automatic Generation:");
		print("- genP(l)			: to generate P with size l bits");
		print("- genPriKeys()		: to generate Alice, Bob and Eve's private keys");
		print("- genPubKeys()		: to generate Alice, Bob and Eve's public keys");
		print("- genSessionKey(pubKey, priKey) : Generate a session key for the given public and private key pair");
		print("- dhExchange()		: Perform the standard Diffie-Hellman exchange between Alice and Bob");
		print("- manInTheMiddle()	: Perform Diffie-Hellman but with Eve performing a man-in-the-middle attack");
}

/* Variables Setting */
P = 2; /* The first prime */
alpha = 0;
pubKA = pubKB = pubKE = 0;
priKA = priKB = priKE = 0;
sesKA = sesKB = sesKEA = sesKEB = 0; /* The session keys */

/* Setting a new P */
{
	setP(myP) =
		if (isprime(myP),
			P = myP,
			print("Sorry, P must be a prime number!"));
}

/* Setting a new primitive element */
{
	setAlpha(myAlpha) =
		if (myAlpha^((P-1)/2) % P == P-1,
			alpha = myAlpha,
			print("Sorry, alpha must be a primitive element!"));
}

/* Set new public keys */
{
	setPubKeyA(myPubKey) =
		if (myPubKey == fastExp(alpha, priKA, P),
			pubKA = myPubKey,
			print("Sorry the public key must be alpha^private key mod P"));
}

{
	setPubKeyB(myPubKey) =
		if (myPubKey == fastExp(alpha, priKB, P),
			pubKB = myPubKey,
			print("Sorry the public key must be alpha^private key mod P"));
}

{
	setPubKeyE(myPubKey) =
		if (myPubKey == fastExp(alpha, priKE, P),
			pubKE = myPubKey,
			print("Sorry the public key must be alpha^private key mod P"));
}

/* Set new private keys */
{
	setPriKeyA(myPriKey) =
		if (myPriKey >= 1 && myPriKey <= P,
			priKA = myPriKey,
			print("1 <= priKey <= P must be satisfied"));
}

{
	setPriKeyB(myPriKey) =
		if (myPriKey >= 1 && myPriKey <= P,
			priKB = myPriKey,
			print("1 <= priKey <= P must be satisfied"));
}

{
	setPriKeyE(myPriKey) =
		if (myPriKey >= 1 && myPriKey <= P,
			priKE = myPriKey,
			print("1 <= priKey <= P must be satisfied"));
}

/* Automatic generation of P */
{
	genP(l) =
		q = 1;
		while(log(q) / log(2) < l - 2,
			q = nextprime(random(2^ceil(l - 1))));
		P = 2 * q + 1; /* generate safe P */
		print("P = ", P);
		alpha1 = znprimroot(P);
		alpha = Pol(lift(alpha1));
		print("alpha = ", alpha);
}

/* Generate private key */
{
	genPriKey() =
		random(P) + 1; /* 1 <= key <= P */
}

/* Generate the private keys */
{
	genPriKeys() =
		priKA = genPriKey();
		print("Alice's private key:\t", priKA);
		priKB = genPriKey();
		print("Bob's private key:\t", priKB);
		priKE = genPriKey();
		print("Eve's private key:\t", priKE);
}

/* Calculate a public key */
{
	genPubKey(priKey) =
		fastExp(alpha, priKey, P);
}

/* Memory and speed efficient exponentiation with modulus */
{
	fastExp(base, power, modulo) =
		res = 1;
		while(power > 0,
			if((power % 2) == 0,
				power = power / 2;
				base = Pol(lift(Mod((base * base), modulo))),
				power = power - 1;
				res = Pol(lift(Mod((base * res), modulo)));));
		res;
}

/* Generate the public Keys */
{
	genPubKeys() =
		pubKA = genPubKey(priKA);
		print("Alice's public key:\t", pubKA);
		pubKB = genPubKey(priKB);
		print("Bob's public key:\t", pubKB);
		pubKE = genPubKey(priKE);
		print("Eve's public key:\t", pubKE);
}

/* Generate a session key */
{
	genSessionKey(pubKey, priKey) =
		fastExp(pubKey, priKey, P);
}

/* A normal key exchange between Alice and Bob */
{
	dhExchange() =
		print("Performing a non-intercepted Diffie-Hellman Key exchange between Alice and Bob");
		genP(32);
		genPriKeys();
		genPubKeys();
		print("Alice uses Bob's public key to calculate the session key");
		sesKA = genSessionKey(pubKB, priKA);
		print("Alice's session key:\t", sesKA);
		print("Bob uses Alice's public key to calculate the session key");
		sesKB = genSessionKey(pubKA, priKB);
		print("Bob's session key:\t", sesKB);
}

/* A man-in-the-middle attack performed by Alice */
{
	manInTheMiddle() =
		print("Performing a Diffie-Hellman Key exchange between Alice and Bob intercepted by Eve as the man-in-the-middle");
		genP(32);
		genPriKeys();
		genPubKeys();
		/* interceptPubKeys(); */
		print("Alice uses Bob's public key  (intercepted to be Eve's public key) to calculate the session key");
		sesKA = genSessionKey(pubKE, priKA);
		print("Alice's session key (shared key between Alice and Eve):\t", sesKA);
		print("Bob uses Alice's public key (intercepted to be Eve's public key) to calculate the session key");
		sesKB = genSessionKey(pubKE, priKB);
		print("Bob's session key (shared key between Bob and Eve):\t", sesKB);
		print("Eve uses the public keys to generate session keys for intercepted communication with Bob and Alice");
		sesKEA = genSessionKey(pubKA, priKE);
		print("Eve's session key for Alice:\t", sesKEA);
		sesKEB = genSessionKey(pubKB, priKE);
		print("Eve's session key for Bob:\t", sesKEB);
}

dhExchange();
print;
manInTheMiddle();