// Just to show you what this looks like in rust
use std::{
    ops::{Add, Sub, Mul, Div},
    fmt,
    error::Error,
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
    pub fn new(mut numer: i32, mut denom: i32) -> Result<RationalNumber, ErrorKind> {
        if denom == 0 {
            Err(ErrorKind::ZeroDenominator)
        }
        else {
            numer *= denom.signum();
            denom = denom.abs();
            let gcd = gcd(numer, denom).abs();
            println!("{}, {}, {}", numer, denom, gcd);
            Ok(RationalNumber { 
                numer: numer / gcd, 
                denom: denom / gcd,
            })
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
        RationalNumber::new(self.numer * o.numer, self.denom * o.denom).unwrap()
    }
}

impl Div for RationalNumber {
    type Output = Self;

    fn div(self, o: Self) -> Self {
        RationalNumber::new(self.numer * o.denom, self.denom * o.numer).unwrap()
    }
}

impl fmt::Display for RationalNumber {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        write!(f, "{}/{}", self.numer, self.denom)
    }
}

#[derive(Copy, Clone, Debug)]
pub enum ErrorKind {
    ZeroDenominator,
}

impl Error for ErrorKind { }

impl fmt::Display for ErrorKind {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        use ErrorKind::*;

        match self {
            ZeroDenominator => write!(f, "You attempted to create a rational number with a denominator of 0"),
            //NumberTooLarge => write!(f, "Internally, both need to be within the u32 limit"),
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn declare() {
        let a = RationalNumber::new(1, 2).unwrap();
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
        let a = RationalNumber::new(1, -2).unwrap();
        assert_eq!(a.numer, -1);
        assert_eq!(a.denom, 2);
    }

    #[test]
    fn simplify() {
        let a = RationalNumber::new(2, 4).unwrap();
        assert_eq!(a.numer, 1);
        assert_eq!(a.denom, 2);
    }

    #[test]
    fn eq() {
        assert_eq!(RationalNumber::new(1, 2).unwrap(), RationalNumber::new(1, 2).unwrap());
    }

    #[test]
    fn add() {
        let a = RationalNumber::new(1, 2).unwrap();
        let b = RationalNumber::new(2, 3).unwrap();
        assert_eq!(a + b, RationalNumber::new(7, 6).unwrap());
    }
    #[test]
    fn sub() {
        let a = RationalNumber::new(1, 2).unwrap();
        let b = RationalNumber::new(2, 3).unwrap();
        assert_eq!(a - b, RationalNumber::new(-1, 6).unwrap());
    }

    #[test]
    fn mul() {
        let a = RationalNumber::new(1, 2).unwrap();
        let b = RationalNumber::new(2, 3).unwrap();
        assert_eq!(a * b, RationalNumber::new(1, 3).unwrap());
    }
    
    #[test]
    fn div() {
        let a = RationalNumber::new(1, 2).unwrap();
        let b = RationalNumber::new(2, 3).unwrap();
        assert_eq!(a / b, RationalNumber::new(3, 4).unwrap());
    }

    #[test]
    fn to_string() {
        let a = RationalNumber::new(1, 2).unwrap();
        assert_eq!(a.to_string(), "1/2");
    }
}