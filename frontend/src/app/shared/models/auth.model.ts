/**
 * Authentication response model
 */
export interface AuthResponse {
  token: string;
  type: string;
  email: string;
  username: string;
  firstName: string;
  lastName: string;
  id: string;
}

/**
 * User information model
 */
export interface User {
  id: string;
  email: string;
  username: string;
  firstName: string;
  lastName: string;
  emailVerified?: boolean;
}

/**
 * Signup request payload
 */
export interface SignUpRequest {
  email: string;
  username: string;
  password: string;
  firstName: string;
  lastName: string;
}

/**
 * Login request payload
 */
export interface LoginRequest {
  email: string;
  password: string;
}

/**
 * Error response model
 */
export interface ErrorResponse {
  message: string;
  status: number;
  timestamp: string;
  path: string;
}
