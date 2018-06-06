extern crate rand;
use rand::Rng;
/**
 * A Program for rsa encryption and decryption
 * And a test of dealing with large numbers
 */
fn main() {
    let key = choose_pq();
    let e_of_m = encrypt(&vec![3], &key);
    for digit in e_of_m {
        print!("{}", digit);
    }
    println!();
}
pub fn encrypt(plaintext: &Vec<i8>, key: &(Vec<i8>, Vec<i8>)) -> Vec<i8> {
    crypt(&plaintext, &key)
}
pub fn decrypt(ciphertext: &Vec<i8>, key: &(Vec<i8>, Vec<i8>)) -> Vec<i8> {
    crypt(&ciphertext, &key)
}
fn crypt(text: &Vec<i8>, key: &(Vec<i8>, Vec<i8>)) -> Vec<i8> {
    fastexp(&text, &key.0, &key.1)
}
fn choose_pq() -> (Vec<i8>, Vec<i8>) {
    let p = find_a_prime();
    let q = find_a_prime();
    if compare(&p, &q) == 0 {
        return choose_pq();
    }
    (p, q)
}
fn choose_d(p: &Vec<i8>, q: &Vec<i8>) -> Vec<i8> {
    let d = find_a_prime();
    if compare(&p, &q) == -1 {
        if compare(&d, &p) != -1 {
            return choose_d(&p, &q);
        }
        return d;
    } else {
        if compare(&d, &q) != -1 {
            return choose_d(&p, &q);
        }
        return d;
    }
}
fn choose_e(d: &Vec<i8>, p: &Vec<i8>, q: &Vec<i8>) -> Vec<i8> {
    let p_minus_one = subtract(&p, &vec![1]);
    let q_minus_one = subtract(&q, &vec![1]);
    let phi = product(&p_minus_one, &q_minus_one);
    let n = product(&p, &q);
    fastexp(&d, &phi, &n)
}
fn fastexp(base: &Vec<i8>, index: &Vec<i8>, modulo: &Vec<i8>) -> Vec<i8> { // fast exponentiation algorithm
    let mut res: Vec<i8> = vec![1];
    let mut base = base.clone();
    let mut index = index.clone();
    while index.len() >= 1 {
        if index.len() == 1 && index[0] == 1{
            return res;
        }
        let even_test = modulus(&index, &vec![2]);
        if even_test.len() == 1 && even_test[0] == 0 {
            let square = product(&base, &base);
            base = modulus(&square, &modulo);
            index = half(&index);
        } else {
            let square = product(&res, &base);
            res = modulus(&square, &modulo);
            index = subtract(&index, &vec![1]);
        }
        
    }
    res
}
fn min(a: usize, b: usize) -> usize {
    if a < b {
        a
    } else {
        b
    }
}
fn max(a: usize, b: usize) -> usize {
    if a > b {
        a
    } else {
        b
    }
}
fn find_a_prime() -> Vec<i8> {
    let mut prime: Vec<i8> = Vec::new();
    for _i in 0..100 {
        prime.push(rand::thread_rng().gen_range(0, 10));
    }
    if (prime[99] % 2) == 1 || !is_prime(&prime){
        return find_a_prime();
    }
    prime
}
fn add(a: &Vec<i8>, b: &Vec<i8>) -> Vec<i8> {
    let mut c: Vec<i8> = Vec::new();
    let (a_offset, b_offset) = if a.len() > b.len() { (a.len() - b.len(), 0) } else { (0, b.len() - a.len()) };
    let max_offset = max(a_offset, b_offset);
    for i in 0..max_offset {
        if max_offset == a_offset {
            c.push(a[i]);
        } else {
            c.push(b[i]);
        }
    }
    let mut carry = 0;
    let mut sum_piece: Vec<i8> = Vec::new();
    for i in (0..min(a.len(), b.len())).rev() {
        let mut summand = a[a_offset + i] + b[b_offset + i] + carry;
        if summand >= 10 {
            summand -= 10;
            carry = 1;
        } else {
            carry = 0;
        }
        sum_piece.push(summand);
    }
    for i in (0..sum_piece.len()).rev() {
        c.push(sum_piece[i]);
    }
    c
}
fn product(a: &Vec<i8>, b: &Vec<i8>) -> Vec<i8> {
    let mut c = a.clone();
    for i in 0..b.len() {
        for j in 0..b[i] {
            let mut lhs = a.clone();
            for k in 0..i {
                lhs.push(0);
            }
            c = add(&c, &lhs);
        }
    }
    c
}
fn subtract(a: &Vec<i8>, b: &Vec<i8>) -> Vec<i8> {
    let mut c: Vec<i8> = Vec::new();
    let (a_offset, b_offset) = if a.len() > b.len() { (a.len() - b.len(), 0) } else { (0, b.len() - a.len()) };
    let max_offset = max(a_offset, b_offset);
    for i in 0..max_offset {
        if max_offset == a_offset {
            c.push(a[i]);
        } else {
            c.push(b[i]);
        }
    }
    let mut carry = 0;
    let mut sum_piece: Vec<i8> = Vec::new();
    for i in (0..min(a.len(), b.len())).rev() {
        let mut summand = a[a_offset + i] - b[b_offset + i] - carry;
        if summand < 0 {
            summand += 10;
            carry = 1;
        } else {
            carry = 0;
        }
        sum_piece.push(summand);
    }
    for i in (0..sum_piece.len()).rev() {
        c.push(sum_piece[i]);
    }
    c
}
fn compare(a: &Vec<i8>, b: &Vec<i8>) -> isize {
    if a.len() > b.len() {
        -1
    } else if a.len() < b.len() {
        1
    } else {
        for i in 0..a.len() { // both lengths are the same
            if a[i] < b[i] {
                return -1;
            } else if a[i] > b[i] {
                return 1;
            }
        }
        return 0;
    }
}
fn modulus(a: &Vec<i8>, b: &Vec<i8>) -> Vec<i8> {
    let mut c = a.clone();
    while compare(&c, &b) != -1 {  
        c = subtract(&c, &b);
    }
    c
}
fn half(a: &Vec<i8>) -> Vec<i8> { // Assumes number is even
    let mut c: Vec<i8> = Vec::new();
    let mut carry = 0;
    for i in 0..a.len() {
        let mut og_sum = a[i] + carry;
        let summand = if og_sum % 2 == 0 {
            carry = 0;
            og_sum / 2 
        } else if og_sum == 0 {
            carry = 5;
            0
        } else {
            og_sum -= 1;
            carry = 10;
            og_sum / 2
        };
        if !(c.len() == 0 && summand == 0) {
            c.push(summand);
        }
    }
    c
}
fn is_prime(n: &Vec<i8>) -> bool {
    let i: Vec<i8> = vec![0];
    while compare(&i, &n) != 0 {
        if modulus(&n, &i)[0] == 0 {
            return false;
        }
    }
    true
}
