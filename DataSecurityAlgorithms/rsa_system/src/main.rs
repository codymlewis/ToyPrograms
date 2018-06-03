extern crate rand;
use rand::Rng;
/**
 * A Program for rsa encryption and decryption
 */
fn main() {
    println!("Hello, world!");
}
pub fn encrypt(plaintext: isize, key: (isize, isize)) -> isize {
    crypt(plaintext, key)
}
pub fn decrypt(ciphertext: isize, key: (isize, isize)) -> isize {
    crypt(ciphertext, key)
}
fn crypt(text: isize, key: (isize, isize)) -> isize {
    fastexp(text, key.0, key.1)
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
    let mut p_minus_one = p.clone;
    if p_minus_one[p_minus_one.len() - 1] == 0 {
        
    } else {
        p_minus_one[p_minus_one.len() - 1] = p_minus_one[p_minus_one.len() - 1] - 1;
    }
}
fn fastexp(base: isize, index: isize, modulo: isize) -> isize { // fast exponentiation algorithm
    let mut res = 1;
    let mut base = base;
    let mut index = index;
    while index > 1 {
        if (index % 2) == 0 {
            base = (base * base) % modulo;
            index /= 2;
        } else {
            res = (res * base) % modulo;
            index -= 1;
        }
        if base == 1 {
            return res;
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
    for i in 0..min(a.len(), b.len()) {
        c.push(a[a_offset + i] + b[b_offset + i]);
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
    for i in 0..min(a.len(), b.len()) {
        if b[b_offset + i] != 0 {
            c.push(a[a_offset + i] - b[b_offset + i]);
        } else {

        }
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
fn is_prime(n: &Vec<i8>) -> bool {
    let i: Vec<i8> = vec![0];
    while compare(&i, &n) != 0 {
        if modulus(&n, &i)[0] == 0 {
            return false;
        }
    }
    true
}
