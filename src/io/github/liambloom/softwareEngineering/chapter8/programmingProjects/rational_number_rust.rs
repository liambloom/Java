// Just to show you what this looks like in rust
use std::{
    ops::{Add, Sub, Mul, Div},
    fmt,
    default::Default,
};

fn gcd(a: i32, b: i32) -> i32 {
    if b == 0 {
        a
    }
    else {
        gcd(b, a % b)
    }
}

fn lcm(a: i32, b: i32) -> i32 {
    a * (b / gcd(a, b))
}

#[derive(Copy, Clone, Debug, PartialEq, Eq)]
pub struct RationalNumber {
    numer: i32,
    denom: i32,
}

impl RationalNumber {
    pub fn new(mut numer: i32, mut denom: i32) -> RationalNumber {
        if denom == 0 {
            panic!("Fraction {}/{} is illegal because it has a denominator of 0", numer, denom);
        }
        numer *= denom.signum();
        denom = denom.abs();
        let gcd = gcd(numer, denom).abs();
        println!("{}, {}, {}", numer, denom, gcd);
        RationalNumber { 
            numer: numer / gcd, 
            denom: denom / gcd,
        }
    }
}

impl Default for RationalNumber {
    fn default() -> Self {
        Self { numer: 0, denom: 1 }
    }
}

impl Add for RationalNumber {
    type Output = Self;

    fn add(self, o: Self) -> Self {
        let lcm = lcm(self.denom, o.denom);

        Self {
            numer: self.numer * lcm / self.denom + o.numer * lcm / o.denom,
            denom: lcm
        }
    }
}

impl Sub for RationalNumber {
    type Output = Self;

    fn sub(self, o: Self) -> Self {
        let lcm = lcm(self.denom, o.denom);

        Self {
            numer: self.numer * lcm / self.denom - o.numer * lcm / o.denom,
            denom: lcm
        }
    }
}

impl Mul for RationalNumber {
    type Output = Self;

    fn mul(self, o: Self) -> Self {
        RationalNumber::new(self.numer * o.numer, self.denom * o.denom)
    }
}

impl Div for RationalNumber {
    type Output = Self;

    fn div(self, o: Self) -> Self {
        RationalNumber::new(self.numer * o.denom, self.denom * o.numer)
    }
}

impl fmt::Display for RationalNumber {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        write!(f, "{}/{}", self.numer, self.denom)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn declare() {
        let a = RationalNumber::new(1, 2);
        assert_eq!(a.numer, 1);
        assert_eq!(a.denom, 2);
    }

    #[test]
    fn default() {
        let a = RationalNumber::default();
        assert_eq!(a.numer, 0);
        assert_eq!(a.denom, 1);
    }

    #[test]
    fn numer_neg() {
        let a = RationalNumber::new(1, -2);
        assert_eq!(a.numer, -1);
        assert_eq!(a.denom, 2);
    }

    #[test]
    fn simplify() {
        let a = RationalNumber::new(2, 4);
        assert_eq!(a.numer, 1);
        assert_eq!(a.denom, 2);
    }

    #[test]
    fn eq() {
        assert_eq!(RationalNumber::new(1, 2), RationalNumber::new(1, 2));
    }

    #[test]
    fn add() {
        let a = RationalNumber::new(1, 2);
        let b = RationalNumber::new(2, 3);
        assert_eq!(a + b, RationalNumber::new(7, 6));
    }
    #[test]
    fn sub() {
        let a = RationalNumber::new(1, 2);
        let b = RationalNumber::new(2, 3);
        assert_eq!(a - b, RationalNumber::new(-1, 6));
    }

    #[test]
    fn mul() {
        let a = RationalNumber::new(1, 2);
        let b = RationalNumber::new(2, 3);
        assert_eq!(a * b, RationalNumber::new(1, 3));
    }
    
    #[test]
    fn div() {
        let a = RationalNumber::new(1, 2);
        let b = RationalNumber::new(2, 3);
        assert_eq!(a / b, RationalNumber::new(3, 4));
    }

    #[test]
    fn to_string() {
        let a = RationalNumber::new(1, 2);
        assert_eq!(a.to_string(), "1/2");
    }
}